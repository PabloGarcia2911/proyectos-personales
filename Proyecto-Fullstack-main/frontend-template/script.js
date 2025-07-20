const API_URL = "http://localhost:8080/api/productos";

document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();

    const form = document.getElementById("productoForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const producto = {
            nombre: document.getElementById("nombre").value,
            precio: parseFloat(document.getElementById("precio").value),
            categoria: document.getElementById("categoria").value,
            proveedor: document.getElementById("proveedor").value
        };

        await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        });

        form.reset();
        cargarProductos();
    });
});

async function cargarProductos() {
    const respuesta = await fetch(API_URL);
    const productos = await respuesta.json();

    const tabla = document.getElementById("productosTabla");
    tabla.innerHTML = "";

    productos.forEach(p => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${p.id}</td>
            <td>${p.nombre}</td>
            <td>${p.precio}</td>
            <td>${p.categoria}</td>
            <td>${p.proveedor}</td>
        `;
        tabla.appendChild(fila);
    });
}
