import datetime

from django.db import models
from django.utils import timezone


class Grau(models.Model):
    sigla = models.CharField(max_length=10, unique=True, editable=False)

    def __str__(self):
        return f"{self.pk}---{self.sigla}"


class Tecnico(models.Model):
    nome = models.CharField(max_length=200)
    data_nascimento = models.DateField('Data nascimento')
    totalEventos = models.IntegerField('Total eventos', default=0)
    totalAmbulancias = models.IntegerField('Total ambulancias', default=0)
    totalMotos = models.IntegerField('Total motos', default=0)
    totalVeiculos = models.IntegerField('Total veículos', default=0)
    totalHelicopteros = models.IntegerField('Total helicópteros', default=0)

    def __str__(self):
        return f"{self.nome}---{self.pk}"


class Posto(models.Model):
    descricao = models.CharField('Descrição', primary_key=True, max_length=200)
    x = models.IntegerField('Coordenada x', default=0)
    y = models.IntegerField('Coordenada y', default=0)
    totalMobilizacoes = models.IntegerField('Total Mobilizacoes', default=0)

    def __str__(self):
        return f"{'Posto:'} {self.pk}"


class Evento(models.Model):
    nomeDoente = models.CharField('Nome Doente', max_length=200, blank=True, null=True)
    idadeDoente = models.IntegerField('Idade do doente', default=0, blank=True, null=True)
    descricao = models.CharField('Descrição', max_length=500, blank=True, null=True)
    grau = models.ForeignKey(Grau, on_delete=models.CASCADE)
    timestampAtiv = models.DateTimeField('Timestamp ativação')  # , default=timezone.now())
    timestampConcl = models.DateTimeField('Timestamp conclusão', blank=True, null=True)
    tecnico = models.ForeignKey(Tecnico, on_delete=models.CASCADE)
    localx = models.IntegerField('Coordenada x', default=0)
    localy = models.IntegerField('Coordenada y', default=0)

    def __str__(self):
        return f"{'Evento'} {self.pk}: Doente - {self.nomeDoente}"

    def eventos_recentes(self):
        return self.timestampConcl >= timezone.now() - datetime.timedelta(days=2)


class MeioSocorro(models.Model):
    localOrigem = models.ForeignKey(Posto, on_delete=models.CASCADE, default='Cucaracha')
    disponivel = models.BooleanField('Disponível', default=True)
    eventos = models.ManyToManyField(Evento, blank=True)
    totalMobilizacoes = models.IntegerField('Total de mobilizações', default=0)
    totalDistancia = models.IntegerField('Total de distância (km)', default=0)

    class Meta:
        abstract = True


class Ambulancia(MeioSocorro):
    tipo = models.CharField(default="Ambulancia", editable=False, max_length=50)
    eventoAtual = models.ForeignKey(Evento, on_delete=models.CASCADE, blank=True, null=True, related_name='evento_atual_amb')

    def __str__(self):
        return f"{'Ambulancia'} {self.pk}-- Disponível: {self.disponivel}"


class Moto(MeioSocorro):
    tipo = models.CharField(default="Moto", editable=False, max_length=50)
    eventoAtual = models.ForeignKey(Evento, on_delete=models.CASCADE, blank=True, null=True, related_name='evento_atual_mot')

    def __str__(self):
        return f"{'Moto'} {self.pk}-- Disponível: {self.disponivel}"


class Veiculo(MeioSocorro):
    tipo = models.CharField(default="Veiculo", editable=False, max_length=50)
    eventoAtual = models.ForeignKey(Evento, on_delete=models.CASCADE, blank=True, null=True, related_name='evento_atual_vei')

    def __str__(self):
        return f"{'Veiculo'} {self.pk}-- Disponível: {self.disponivel}"


class Helicoptero(MeioSocorro):
    tipo = models.CharField(default="Helicoptero", editable=False, max_length=50)
    eventoAtual = models.ForeignKey(Evento, on_delete=models.CASCADE, blank=True, null=True, related_name='evento_atual_hel')

    def __str__(self):
        return f"{'Helicoptero'} {self.pk}-- Disponível: {self.disponivel}"
