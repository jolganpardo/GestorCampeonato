# Gestor Campeonato
## Colaboradores:
- Jolgan Pardo
- Yisus Olarte

## 1. Principio SRP

### Violaciones
La clase `GestorCampeonato` tiene multiples responsabilidades:
- Registro de equipos y árbitros.
- Cálculo de bonificaciones.
- Generación de reportes.

Esto hace que sea dificil de mantener.

---

## 2. Principio OCP

### Violacion
El método `generarReportes` usa condicionales para definir el formato.  
Si se agrega el formato PDF, se tendria que modificar el metodo, violando el principio de Abierto/Cerrado.  
Abierto a extensiones cerrado a modificaciones.

---

## 3. Principio LSP
El principio LSP dice: “Si una clase B hereda de una clase A, entonces deberia poder usarse en lugar de A sin romper la aplicacion”.

Lo ideal para este caso es que las posiciones sean una subclase de Jugador y que cada una sepa calcular su propia bonificacion.  
Así cualquier "Jugador" pueda usarse de forma "Polimorfica" ya sea Portero o Delantero.

### Violaciones
- Además de estar sujeto a que se haga una comparacion de tipo if/else violando el principio OCP (Abierto/Cerrado).  
- Está violando también el principio LSP.

---

## 4. Principio ISP
