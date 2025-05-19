import random
import string

def generar_contrasena(longitud=12, incluir_mayusculas=True, incluir_minusculas=True, incluir_numeros=True, incluir_simbolos=True):
    caracteres = ''
    if incluir_mayusculas:
        caracteres += string.ascii_uppercase
    if incluir_minusculas:
        caracteres += string.ascii_lowercase
    if incluir_numeros:
        caracteres += string.digits
    if incluir_simbolos:
        caracteres += string.punctuation

    if not caracteres:
        return "¡Error! Debe seleccionar al menos un tipo de carácter."

    contrasena = ''.join(random.choice(caracteres) for _ in range(longitud))
    return contrasena

if __name__ == "__main__":
    longitud_deseada = int(input("Ingrese la longitud deseada para la contraseña: "))
    incluir_mayus = input("¿Incluir mayúsculas? (s/n): ").lower() == 's'
    incluir_minus = input("¿Incluir minúsculas? (s/n): ").lower() == 's'
    incluir_nums = input("¿Incluir números? (s/n): ").lower() == 's'
    incluir_simb = input("¿Incluir símbolos? (s/n): ").lower() == 's'

    contrasena_generada = generar_contrasena(longitud_deseada, incluir_mayus, incluir_minus, incluir_nums, incluir_simb)
    print("Contraseña generada:", contrasena_generada)