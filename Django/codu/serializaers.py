from rest_framework import serializers
from rest_framework.permissions import AllowAny
from codu.models import *


class GrauSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Grau
        fields = ["id", "sigla"]


class TecnicoSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Tecnico
        fields = ["id", "nome", "data_nascimento", "totalEventos", "totalAmbulancias", "totalMotos", "totalVeiculos", "totalHelicopteros"]


class PostoSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Posto
        fields = ["descricao", "x", "y", "totalMobilizacoes"]


class EventoSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Evento
        fields = ["id", "nomeDoente", "idadeDoente", "descricao", "grau", "timestampAtiv", "timestampConcl", "tecnico", "localx", "localy"]


class AmbulanciaSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Ambulancia
        fields = ["tipo", "id", "localOrigem", "disponivel", "eventoAtual", "totalMobilizacoes", "totalDistancia", "eventos"]


class MotoSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Moto
        fields = ["tipo", "id", "localOrigem", "disponivel", "eventoAtual", "totalMobilizacoes", "totalDistancia", "eventos"]


class VeiculoSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Veiculo
        fields = ["tipo", "id", "localOrigem", "disponivel", "eventoAtual", "totalMobilizacoes", "totalDistancia", "eventos"]


class HelicopteroSerializer(serializers.ModelSerializer):
    class Meta:
        permission_classes = [AllowAny]
        model = Helicoptero
        fields = ["tipo", "id", "localOrigem", "disponivel", "eventoAtual", "totalMobilizacoes", "totalDistancia", "eventos"]
