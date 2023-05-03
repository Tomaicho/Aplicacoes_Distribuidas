from django.urls import path

from . import views

app_name = 'codu'
urlpatterns = [
    # REST framework
    path('rest/grau/', views.GrauLista),

    path('rest/tecnico/', views.TecnicoLista),
    path('rest/tecnico/<int:id>/', views.TecnicoDetalhes),

    path('rest/posto/', views.PostoLista),
    path('rest/posto/<str:descricao>/', views.PostoDetalhes),

    path('rest/evento/', views.EventoLista),
    path('rest/evento/<int:id>/', views.EventoDetalhes),

    path('rest/meios/', views.MeioLista),

    path('rest/ambulancia/', views.AmbulanciaLista),
    path('rest/ambulancia/<int:id>/', views.AmbulanciaDetalhes),

    path('rest/moto/', views.MotoLista),
    path('rest/moto/<int:id>/', views.MotoDetalhes),

    path('rest/veiculo/', views.VeiculoLista),
    path('rest/veiculo/<int:id>/', views.VeiculoDetalhes),

    path('rest/helicoptero/', views.HelicopteroLista),
    path('rest/helicoptero/<int:id>/', views.HelicopteroDetalhes),

    # VIEWS
    # index - /codu/
    path('', views.IndexView.as_view(), name='index'),

    # evento - /codu/evento/<int:pk>/
    path('evento/<int:pk>/', views.EventoView.as_view(), name='evento'),

    # posto - /codu/posto/<str:pk>/
    path('posto/<str:pk>/', views.PostoView.as_view(), name='posto'),

    # tencino - login: /tecnico/; página: /tecnico/<int:pk>/
    path('tecnico/', views.TecnicoLoginView.as_view(), name='tecnico-login'),
    path('tecnico/<int:pk>/', views.TecnicoView.as_view(), name='tecnico-page'),

    # meio de socorro - login: /meio/; página: /meio/<str:tipo>/<int:pk>
    path('meio/', views.MeioLoginView.as_view(), name='meio-login'),
    path('meio/ambulancia/<int:pk>/', views.AmbulanciaView.as_view(), name='tecnico-page'),
    path('meio/moto/<int:pk>/', views.MotoView.as_view(), name='tecnico-page'),
    path('meio/veiculo/<int:pk>/', views.VeiculoView.as_view(), name='tecnico-page'),
    path('meio/helicoptero/<int:pk>/', views.HelicopteroView.as_view(), name='tecnico-page'),
]
