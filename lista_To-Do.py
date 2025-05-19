def mostrar_tareas(tareas):
    if not tareas:
        print("No hay tareas pendientes.")
    else:
        print("\nLista de Tareas:")
        for i, tarea in enumerate(tareas):
            print(f"{i+1}. {tarea}")

def agregar_tarea(tareas):
    nueva_tarea = input("Ingrese la nueva tarea: ")
    tareas.append(nueva_tarea)
    print(f"Tarea '{nueva_tarea}' agregada.")

def marcar_completada(tareas):
    mostrar_tareas(tareas)
    try:
        indice = int(input("Ingrese el número de la tarea completada: ")) - 1
        if 0 <= indice < len(tareas):
            print(f"Tarea '{tareas.pop(indice)}' marcada como completada.")
        else:
            print("¡Número de tarea inválido!")
    except ValueError:
        print("¡Entrada inválida! Por favor, ingrese un número.")

def eliminar_tarea(tareas):
    mostrar_tareas(tareas)
    try:
        indice = int(input("Ingrese el número de la tarea a eliminar: ")) - 1
        if 0 <= indice < len(tareas):
            print(f"Tarea '{tareas.pop(indice)}' eliminada.")
        else:
            print("¡Número de tarea inválido!")
    except ValueError:
        print("¡Entrada inválida! Por favor, ingrese un número.")

tareas = []

while True:
    print("\nOpciones:")
    print("1. Mostrar tareas")
    print("2. Agregar tarea")
    print("3. Marcar como completada")
    print("4. Eliminar tarea")
    print("5. Salir")

    opcion = input("Selecciona una opción (1-5): ")

    if opcion == '1':
        mostrar_tareas(tareas)
    elif opcion == '2':
        agregar_tarea(tareas)
    elif opcion == '3':
        marcar_completada(tareas)
    elif opcion == '4':
        eliminar_tarea(tareas)
    elif opcion == '5':
        print("¡Gracias por usar la lista de tareas!")
        break
    else:
        print("¡Opción inválida! Por favor, seleccione una opción válida.")