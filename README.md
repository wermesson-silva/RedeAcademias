
<h1 align="center"> Fic Fino · Rede de Academias</h1>

<p align="center">
  Sistema web para gerenciamento de academias, personais e clientes.
</p>

<p align="center">
  <img alt="Play Framework" src="https://img.shields.io/badge/Play%20Framework-1.5.3-92D13D?style=for-the-badge&logo=playframework&logoColor=white">
  <img alt="Java" src="https://img.shields.io/badge/Java-8%20%7C%2011-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img alt="Bootstrap" src="https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img alt="H2 Database" src="https://img.shields.io/badge/H2-em%20mem%C3%B3ria-1021FF?style=for-the-badge">
</p>

<p align="center">
  <a href="#-sobre-o-projeto">Sobre</a> •
  <a href="#-funcionalidades">Funcionalidades</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-como-executar">Como executar</a> •
  <a href="#-usuários-de-teste">Usuários de teste</a> •
  <a href="#-estrutura-do-projeto">Estrutura</a>
</p>

---

## Sobre o projeto

O **Fic Fino** é uma aplicação web desenvolvida em Play Framework 1.5.3 (Java). Ela permite cadastrar e acompanhar academias, personais e clientes, com controle de acesso por tipo de usuário.

## Funcionalidades

- Autenticação com login, senha e criação de conta (cliente ou personal).
- Três perfis de acesso:

| Perfil | O que pode fazer |
|---|---|
| **Administrador** | Gerencia academias, personais e clientes (cadastro, edição, listagem e remoção) e vincula personais às academias. |
| **Personal** | Cadastra seus dados, escolhe as academias em que trabalha e os clientes que acompanha. |
| **Cliente** | Cadastra seus dados, escolhe sua academia e seu personal. |

- CRUD completo de academias, personais e clientes.
- Busca em tempo real nas listagens (AJAX).
- Validações: duplicidade de nome, CPF/CNPJ e contato, idade mínima e regras de remoção (não remove academia com clientes nem personal vinculado).
- Máscaras de CPF, CNPJ, telefone e salário nos formulários.
- Interface responsiva com Bootstrap 5 e Bootstrap Icons.

## Tecnologias

- Java
- Play Framework 1.5.3
- Hibernate / JPA
- H2 Database (em memória, configuração padrão)
- Bootstrap 5.3
- jQuery
- jQuery Mask
- Bootstrap Icons

## Como executar

### Pré-requisitos

| Ferramenta | Versão |
|---|---|
| JDK | 8 ou 11 |
| Git | Qualquer versão recente |

Verifique as instalações:

```bash
java -version
git --version
```

> [!WARNING]
> O Play Framework 1.5.3 foi desenvolvido para versões antigas do Java. Utilize o JDK 8 ou 11, conforme a compatibilidade do projeto. Versões mais novas podem causar erros de compilação.

### 1 - Instalar o Play Framework 1.5.3

Baixe o Play Framework 1.5.3 em:

- [GitHub Releases — Play Framework 1.5.3](https://github.com/playframework/play1/releases/tag/1.5.3)
- [Site oficial do Play Framework](https://www.playframework.com/download) — seção Play 1.

Extraia o arquivo em uma pasta de sua preferência:

- **Windows:** `C:\play-1.5.3`
- **Linux/macOS:** `~/play-1.5.3`

### 2 - Baixar o projeto

Clone o repositório dentro da pasta do play 1.5.3:

```bash
git clone https://github.com/wermesson-silva/RedeAcademias.git
```

### 3 - Executar a aplicação

Dentro da pasta do play 1.5.3, execute:

```bash
play run RedeAcademias
```

Na primeira execução, o Play compila o código e inicia o servidor.

Quando aparecer a mensagem:

```text
Listening for HTTP on port 9000
```

Acesse a aplicação no navegador:

**http://localhost:9000**

## Comandos úteis

| Comando | Descrição |
|---|---|
| `play run` | Inicia a aplicação em modo de desenvolvimento, com recarga automática ao salvar arquivos. |
| `play test` | Executa a aplicação no modo de testes. |
| `play clean` | Limpa arquivos temporários e compilados. |
| `play eclipsify` | Gera os arquivos para importar o projeto no Eclipse. |
| `play idealize` | Gera os arquivos para importar o projeto no IntelliJ IDEA. |

## Usuários de teste

Ao iniciar, a aplicação cria dados de exemplo, incluindo academias, um personal e três contas.

| Login | Senha | Perfil |
|---|---|---|
| `admin` | `1234` | Administrador |
| `fulano` | `1234` | Personal |
| `wermesson` | `1234` | Cliente |

Também é possível criar novas contas pela tela **Criar conta**.

> [!IMPORTANT]
> O banco de dados utiliza a configuração em memória (`db.default=mem`). Os dados são recriados quando a aplicação reinicia.
>
> Para persistir os dados, edite o arquivo `conf/application.conf` e configure uma alternativa compatível, como um banco de dados em arquivo ou um servidor MySQL/PostgreSQL, seguindo a documentação e os comentários de configuração do projeto.

## Estrutura do projeto

```text
├── app
│   ├── controllers   # Academias, Clientes, Personais, Logins, Seguranca, Validacao
│   ├── jobs          # Carga inicial de dados (Inicializador)
│   ├── models        # Entidades JPA (Academia, Cliente, Personal, Login, Status)
│   └── views         # Templates HTML do Play
├── conf              # application.conf, routes e dependências
├── public            # CSS, JavaScript e imagens
└── test              # Testes
```

## Importar na IDE

<details>
<summary><b>Eclipse</b></summary>

Na pasta do projeto, execute:

```bash
play eclipsify
```

Depois, no Eclipse, acesse:

`File > Import > Existing Projects into Workspace`

</details>

<details>
<summary><b>IntelliJ IDEA</b></summary>

Na pasta do projeto, execute:

```bash
play idealize
```

Depois, abra ou importe a pasta do projeto no IntelliJ IDEA.

</details>

## Autores

Desenvolvido por **Wermesson** e **Fabrício**
