# Java Expression Engine  
A Lightweight Framework for Building, Evaluating, and Simplifying Math Expressions

This project implements a flexible engine for mathematical expressions in Java.  
Rather than behaving like a traditional “calculator”, the system focuses on the **representation**, **evaluation**, and **symbolic simplification** of expressions using clean OOP design.

The architecture relies heavily on:
- Abstract classes and interfaces  
- Polymorphism and inheritance  
- Composable expression trees  
- Extensible operator hierarchy  

---

## ✨ Capabilities

### ► Expression Modeling  
Build expressions as nested Java objects:
- Numeric values  
- Variables  
- Unary operators (sin, cos, negation)  
- Binary operators (plus, minus, multiplication, division, power)

### ► Evaluation With Variable Assignments  
Expressions can be evaluated using a map of variable values:
```java
Map<String, Double> vars = Map.of("x", 4.0);
double result = expr.evaluate(vars);
