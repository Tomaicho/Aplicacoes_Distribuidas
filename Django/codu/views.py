from django.views import generic
from django.views.decorators.csrf import csrf_exempt
from rest_framework import status
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import AllowAny, DjangoObjectPermissions
from rest_framework.response import Response

from .serializaers import *


# REST framework
# CRUD (Create, Read, Update, Delete)
# Create - POST; Read - GET; Update - PUT; Delete - DELETE

# Grau - Read
@api_view(["GET"])
@permission_classes([AllowAny])
def GrauLista(request):
    if request.method == "GET":
        eventos = Grau.objects.all()
        serializer = GrauSerializer(eventos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)


# Tecnico - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def TecnicoLista(request):
    if request.method == "GET":
        eventos = Tecnico.objects.all()
        serializer = TecnicoSerializer(eventos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = TecnicoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def TecnicoDetalhes(request, id):
    try:
        tecnico = Tecnico.objects.get(pk=id)
    except Tecnico.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = TecnicoSerializer(tecnico)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = TecnicoSerializer(tecnico, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# Posto - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def PostoLista(request):
    if request.method == "GET":
        postos = Posto.objects.all()
        serializer = PostoSerializer(postos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = PostoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def PostoDetalhes(request, descricao):
    try:
        posto = Posto.objects.get(pk=descricao)
    except Posto.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = PostoSerializer(posto)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = PostoSerializer(posto, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# Evento - Create, Read, Update, Delete
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def EventoLista(request):
    if request.method == "GET":
        eventos = Evento.objects.all()
        serializer = EventoSerializer(eventos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = EventoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT", "DELETE"])
@permission_classes([AllowAny])
def EventoDetalhes(request, id):
    try:
        evento = Evento.objects.get(pk=id)
    except Evento.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = EventoSerializer(evento)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = EventoSerializer(evento, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == "DELETE":
        evento.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# Meios de Socorro (conjunto) - Read
@api_view(["GET"])
@permission_classes([AllowAny])
def MeioLista(request):
    if request.method == "GET":
        ambulancias = Ambulancia.objects.all()
        serializer_ambulancias = AmbulanciaSerializer(ambulancias, many=True)
        motos = Moto.objects.all()
        serializer_motos = MotoSerializer(motos, many=True)
        veiculos = Veiculo.objects.all()
        serializer_veiculos = VeiculoSerializer(veiculos, many=True)
        helicopteros = Helicoptero.objects.all()
        serializer_helicopteros = HelicopteroSerializer(helicopteros, many=True)
        return Response([serializer_ambulancias.data, serializer_motos.data, serializer_veiculos.data, serializer_helicopteros.data], status=status.HTTP_200_OK)


# Ambulancia - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def AmbulanciaLista(request):
    if request.method == "GET":
        ambulancias = Ambulancia.objects.all()
        serializer = AmbulanciaSerializer(ambulancias, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = AmbulanciaSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def AmbulanciaDetalhes(request, id):
    try:
        ambulancia = Ambulancia.objects.get(pk=id)
    except Ambulancia.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = AmbulanciaSerializer(ambulancia)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = AmbulanciaSerializer(ambulancia, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# Moto - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def MotoLista(request):
    if request.method == "GET":
        motos = Moto.objects.all()
        serializer = MotoSerializer(motos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = MotoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def MotoDetalhes(request, id):
    try:
        moto = Moto.objects.get(pk=id)
    except Moto.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = MotoSerializer(moto)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = MotoSerializer(moto, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# Veiculo - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def VeiculoLista(request):
    if request.method == "GET":
        veiculos = Veiculo.objects.all()
        serializer = VeiculoSerializer(veiculos, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = VeiculoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def VeiculoDetalhes(request, id):
    try:
        veiculo = Veiculo.objects.get(pk=id)
    except Veiculo.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = VeiculoSerializer(veiculo)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = VeiculoSerializer(veiculo, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# Helicoptero - Create, Read, Update
@api_view(["GET", "POST"])
@permission_classes([AllowAny])
def HelicopteroLista(request):
    if request.method == "GET":
        helicopteros = Helicoptero.objects.all()
        serializer = HelicopteroSerializer(helicopteros, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "POST":
        serializer = HelicopteroSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET", "PUT"])
@permission_classes([AllowAny])
def HelicopteroDetalhes(request, id):
    try:
        helicoptero = Helicoptero.objects.get(pk=id)
    except Helicoptero.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = HelicopteroSerializer(helicoptero)
        return Response(serializer.data, status=status.HTTP_200_OK)

    elif request.method == "PUT":
        serializer = HelicopteroSerializer(helicoptero, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# VIEWS
# index
class IndexView(generic.ListView):
    template_name = 'codu/index.html'
    context_object_name = 'latest_events_list'

    def get_queryset(self):
        """Return the last five concluded events."""
        return Evento.objects.order_by('timestampConcl')[:5]


# evento
class EventoView(generic.TemplateView):
    template_name = 'codu/evento_page.html'


# posto
class PostoView(generic.TemplateView):
    template_name = 'codu/posto_page.html'


# tecnico
class TecnicoLoginView(generic.TemplateView):
    template_name = 'codu/tecnico_login.html'


class TecnicoView(generic.DetailView):
    model = Tecnico
    template_name = 'codu/tecnico_page.html'


# meio
class MeioLoginView(generic.TemplateView):
    template_name = 'codu/meio_login.html'


class AmbulanciaView(generic.TemplateView):
    template_name = 'codu/ambulancia_page.html'


class MotoView(generic.TemplateView):
    template_name = 'codu/moto_page.html'


class VeiculoView(generic.TemplateView):
    template_name = 'codu/veiculo_page.html'


class HelicopteroView(generic.TemplateView):
    template_name = 'codu/helicoptero_page.html'
