def soma_quadrados(lista):
    soma = 0
    for numero in lista:
        soma += numero ** 2
    return soma

print(soma_quadrados([1, 2, 3, 4]))
