# Agenda telefônica



<p align="center">
  <a href="#objetivo">Objetivo</a> •
  <a href="#tecnologia">Tecnologia</a> •
  <a href="#ambiente">Ambiente</a> •
  <a href="#autor">Autor</a>
 </p> 

# Objetivo
• Projeto desenvolvido para incluir, alterar, pesquisar e excluir contatos telefônicos.

# Tecnologia
• Eclipse V. 2020-09 (4.17.0) com o plugin WindowsBuilder 1.9.5 </br>
• MySQL Workbench </br>
• Git

# Ambiente
### Pré requisitos

Antes de iniciar o projeto você deve ter instalado em sua máquina as tecnologia citadas anteriormente (Eclipse, MySQL, Git)

### Rodando o projeto

```bash
# Clonar este repositório em seu workspace do Eclipse
$ git clone https://github.com/siqleomei/agenda-telefonica

# Abra seu eclipse com o workspace deste projeto.

# Crie um schema no MySQL Workbench (localhost, port 3306) com o nome de "bd_agendatelefonica".
# Neste banco, crie a tabela "tb_contato" com os campos:
'id' int PRIMARY KEY AUTO INCREMENT,
'nome' varchar(100),
'telefone' varchar(12),
'endereco' varchar(200)

# Após criar o banco, volte ao eclipse e de um F11 para rodar o projeto.
```

# Autor

• Leonardo Siqueira Mei
