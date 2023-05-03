# Generated by Django 4.1.4 on 2023-01-18 01:43

from django.db import migrations, models
import django.db.models.deletion


def forwards_func(apps, schema_editor):
    Grau = apps.get_model("codu", "Grau")
    db_alias = schema_editor.connection.alias
    Grau.objects.using(db_alias).bulk_create([
        Grau(sigla="SBV"),
        Grau(sigla="SBV_U"),
        Grau(sigla="SAV"),
        Grau(sigla="SAV_U"),
    ])


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Evento',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nomeDoente', models.CharField(blank=True, max_length=200, null=True, verbose_name='Nome Doente')),
                ('idadeDoente', models.IntegerField(blank=True, default=0, null=True, verbose_name='Idade do doente')),
                ('descricao', models.CharField(blank=True, max_length=500, null=True, verbose_name='Descrição')),
                ('timestampAtiv', models.DateTimeField(verbose_name='Timestamp ativação')),
                ('timestampConcl', models.DateTimeField(blank=True, null=True, verbose_name='Timestamp conclusão')),
                ('localx', models.IntegerField(default=0, verbose_name='Coordenada x')),
                ('localy', models.IntegerField(default=0, verbose_name='Coordenada y')),
            ],
        ),
        migrations.CreateModel(
            name='Grau',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('sigla', models.CharField(editable=False, max_length=10, unique=True)),
            ],
        ),
        migrations.CreateModel(
            name='Posto',
            fields=[
                ('descricao', models.CharField(max_length=200, primary_key=True, serialize=False, verbose_name='Descrição')),
                ('x', models.IntegerField(default=0, verbose_name='Coordenada x')),
                ('y', models.IntegerField(default=0, verbose_name='Coordenada y')),
                ('totalMobilizacoes', models.IntegerField(default=0, verbose_name='Total Mobilizacoes')),
            ],
        ),
        migrations.CreateModel(
            name='Tecnico',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=200)),
                ('data_nascimento', models.DateField(verbose_name='Data nascimento')),
                ('totalEventos', models.IntegerField(default=0, verbose_name='Total eventos')),
                ('totalAmbulancias', models.IntegerField(default=0, verbose_name='Total ambulancias')),
                ('totalMotos', models.IntegerField(default=0, verbose_name='Total motos')),
                ('totalVeiculos', models.IntegerField(default=0, verbose_name='Total veículos')),
                ('totalHelicopteros', models.IntegerField(default=0, verbose_name='Total helicópteros')),
            ],
        ),
        migrations.CreateModel(
            name='Veiculo',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('disponivel', models.BooleanField(default=True, verbose_name='Disponível')),
                ('totalMobilizacoes', models.IntegerField(default=0, verbose_name='Total de mobilizações')),
                ('totalDistancia', models.IntegerField(default=0, verbose_name='Total de distância (km)')),
                ('tipo', models.CharField(default='Veiculo', editable=False, max_length=50)),
                ('eventoAtual', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='evento_atual_vei', to='codu.evento')),
                ('eventos', models.ManyToManyField(blank=True, to='codu.evento')),
                ('localOrigem', models.ForeignKey(default='Cucaracha', on_delete=django.db.models.deletion.CASCADE, to='codu.posto')),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='Moto',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('disponivel', models.BooleanField(default=True, verbose_name='Disponível')),
                ('totalMobilizacoes', models.IntegerField(default=0, verbose_name='Total de mobilizações')),
                ('totalDistancia', models.IntegerField(default=0, verbose_name='Total de distância (km)')),
                ('tipo', models.CharField(default='Moto', editable=False, max_length=50)),
                ('eventoAtual', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='evento_atual_mot', to='codu.evento')),
                ('eventos', models.ManyToManyField(blank=True, to='codu.evento')),
                ('localOrigem', models.ForeignKey(default='Cucaracha', on_delete=django.db.models.deletion.CASCADE, to='codu.posto')),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='Helicoptero',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('disponivel', models.BooleanField(default=True, verbose_name='Disponível')),
                ('totalMobilizacoes', models.IntegerField(default=0, verbose_name='Total de mobilizações')),
                ('totalDistancia', models.IntegerField(default=0, verbose_name='Total de distância (km)')),
                ('tipo', models.CharField(default='Helicoptero', editable=False, max_length=50)),
                ('eventoAtual', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='evento_atual_hel', to='codu.evento')),
                ('eventos', models.ManyToManyField(blank=True, to='codu.evento')),
                ('localOrigem', models.ForeignKey(default='Cucaracha', on_delete=django.db.models.deletion.CASCADE, to='codu.posto')),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.AddField(
            model_name='evento',
            name='grau',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='codu.grau'),
        ),
        migrations.AddField(
            model_name='evento',
            name='tecnico',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='codu.tecnico'),
        ),
        migrations.CreateModel(
            name='Ambulancia',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('disponivel', models.BooleanField(default=True, verbose_name='Disponível')),
                ('totalMobilizacoes', models.IntegerField(default=0, verbose_name='Total de mobilizações')),
                ('totalDistancia', models.IntegerField(default=0, verbose_name='Total de distância (km)')),
                ('tipo', models.CharField(default='Ambulancia', editable=False, max_length=50)),
                ('eventoAtual', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='evento_atual_amb', to='codu.evento')),
                ('eventos', models.ManyToManyField(blank=True, to='codu.evento')),
                ('localOrigem', models.ForeignKey(default='Cucaracha', on_delete=django.db.models.deletion.CASCADE, to='codu.posto')),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.RunPython(forwards_func),
    ]