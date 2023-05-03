from django.contrib import admin

from .models import Tecnico, MeioSocorro, Evento, Ambulancia, Moto, Veiculo, Helicoptero, Posto, Grau

admin.site.register(Tecnico)
admin.site.register(Evento)
admin.site.register(Ambulancia)
admin.site.register(Veiculo)
admin.site.register(Moto)
admin.site.register(Helicoptero)
admin.site.register(Posto)
admin.site.register(Grau)
