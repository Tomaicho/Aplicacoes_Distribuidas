from django.db import migrations
from codu.models import Grau


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
    dependencies = []

    operations = [
        migrations.RunPython(forwards_func),
    ]
