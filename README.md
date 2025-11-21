[![Build Status](https://github.com/B3ckeriz/A3gerenciamentoDeAlunos/actions/workflows/maven.yml/badge.svg)](https://github.com/B3ckeriz/A3gerenciamentoDeAlunos/actions) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=B3ckeriz_A3-&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=B3ckeriz_A3-) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=B3ckeriz_A3-&metric=coverage)](https://sonarcloud.io/summary/new_code?id=B3ckeriz_A3-) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=B3ckeriz_A3-&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=B3ckeriz_A3-) 

# Sistema de Gestão de Alunos e Professores  
Projeto A3 – Gestão e Qualidade de Software

[![Vídeo no YouTube](https://img.shields.io/badge/Vídeo-YouTube-red?style=for-the-badge&logo=youtube)](https://youtu.be/t6G6Fhsl-7Q?si=Vju25y4o-gDqt3K0)
[![Slide no Canva](https://img.shields.io/badge/Slide-Canva-00C4CC?style=for-the-badge&logo=canva&logoColor=white)](https://www.canva.com/design/DAG4cErd7xc/fWrRN880e0tIhmFqVcFZ3A/edit?utm_content=DAG4cErd7xc&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
[![Relatório](https://img.shields.io/badge/Relatório-PDF-red?style=for-the-badge)](src/main/java/view/A3%20Gestão%20e%20Qualidade%20de%20Software%20(2).pdf)


## Descrição Geral  
Este repositório contém o desenvolvimento do Plano de Testes e da automação aplicada ao Sistema de Gestão de Alunos e Professores. O projeto utiliza práticas de qualidade de software, incluindo testes automatizados, integração contínua e análise estática do código.

O objetivo principal é demonstrar a aplicação de testes unitários e de integração em um sistema legado, bem como implementar um pipeline de CI/CD completo utilizando Maven, GitHub Actions e SonarCloud.

---

## Tecnologias Utilizadas  
- **Java** – Linguagem principal da aplicação  
- **Maven** – Automação de build e gerenciamento de dependências  
- **JUnit 5** – Framework para criação e execução de testes automatizados  
- **GitHub Actions** – Plataforma de CI/CD para execução automática dos testes  
- **Jacoco** – Ferramenta de medição de cobertura de código  
- **SonarCloud** – Análise estática para avaliação contínua da qualidade  

---



## Integrantes  
| Nome | RA | GitHub |
|------|---------|---------|
| Júlia Schaden Exterkoetter | 10722118399 | https://github.com/juliaexterkoetter |
| Kaio Wellington Farias da Costa | 10724113141 | https://github.com/DyreDev |
| Pedro Furlan Becker | 10722127773 | https://github.com/B3ckeriz |
| Rafaela Araujo Fontoura da Rosa | 10724112362 | https://github.com/RafaArauj |

---

## Estrutura do Projeto  
- `src/main/java` – Implementação do sistema  
- `src/test/java` – Testes unitários  
- `pom.xml` – Configuração do Maven, plugins e dependências  
- `.github/workflows` – Pipelines de CI/CD  
- Integração com Jacoco e SonarCloud configurada para cada push na branch `main`

---

## Requisitos do Sistema
### Requisitos Funcionais 
RF01 – Cadastro, o sistema deve permitir o cadastro de alunos através de um formulário contendo: nome, curso, data de nascimento e fase.

RF02 – Edição, sistema deve permitir editar qualquer informação previamente cadastrada de um aluno.

RF03 – Exclusão, o sistema deve permitir excluir um aluno da base de dados.

RF04 – Listagem, o sistema deve exibir uma tabela com todos os alunos cadastrados.

RF05 – Atualização, tabela de alunos deve refletir alterações quando atualizada.

RF06 – Cadastro, o sistema deve permitir cadastrar professores utilizando os campos: nome, campus, CPF, contato, data de nascimento, salário e título.

RF07 – Edição, o sistema deve permitir modificar os dados de um professor.

RF08 – Exclusão, o sistema deve permitir remover professores da base de dados.

RF09 – Listagem, o sistema deve exibir uma tabela com os professores cadastrados.

RF10 – Atualização, a tabela de professores deve refletir alterações quando atualizada.


### Requisitos Não Funcionais
RNF01 – Tema claro e escuro, o sistema deve oferecer suporte aos temas claro e escuro, permitindo alternância.

RNF02 – Feedback visual, o sistema deve apresentar mensagens de sucesso, erro ou confirmação ao realizar operações de cadastro, edição ou exclusão.
## Objetivo Acadêmico  
Este repositório atende aos requisitos da A3 de Gestão e Qualidade de Software, incluindo:  
- Planejamento e execução de testes  
- Construção de pipelines automatizados  
- Avaliação de métricas de qualidade  
- Documentação técnica conforme normas institucionais  

---
