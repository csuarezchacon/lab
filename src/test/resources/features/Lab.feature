# language: es
# encoding:utf-8

Característica: Prueba de entrevista

  Esquema del escenario: Probando comparador de Gadgetsnow.com
    Dado que ingreso a gadgetsnow.com
    Cuando selecciono el primer laptop "<lap1>"
    Y selecciono el primer laptop "<lap2>"
    Entonces comparo los productos
    Ejemplos:
      | lap1          | lap2          |
      | Lenovo E41-80 | Lenovo B40-80 |