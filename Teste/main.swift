class Pessoa {
  var nome: String
  var idade: Int

  init(nome: String, idade: Int){
    self.nome = nome
    self.idade = idade
  }
}

var p = Pessoa(nome: "Guilherme", idade: 21)

print(p)
print(p.nome)
print(p.idade)