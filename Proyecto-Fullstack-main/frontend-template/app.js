document.getElementById('formulario-producto').addEventListener('submit', async function(e) {
  e.preventDefault();
  const nombre = document.getElementById('nombre').value;
  const precio = parseFloat(document.getElementById('precio').value);
  const stock = parseInt(document.getElementById('stock').value);

  const response = await fetch('http://localhost:8080/api/productos', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, precio, stock })
  });

  if (response.ok) {
    alert('Producto agregado correctamente');
    location.reload();
  } else {
    alert('Error al agregar el producto');
  }
});

async function cargarProductos() {
  const response = await fetch('http://localhost:8080/api/productos');
  const productos = await response.json();
  const lista = document.getElementById('lista-productos');
  productos.forEach(p => {
    const item = document.createElement('li');
    item.textContent = `${p.nombre} - $${p.precio} - Stock: ${p.stock}`;
    lista.appendChild(item);
  });
}

cargarProductos();
