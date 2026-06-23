# Sistema de Gestão para Assistência Social (ERP/CRM)

Projeto desenvolvido com foco na **Assistência Social**, utilizando metodologias modernas como **TDD** (Test-Driven Development) e conceitos de arquitetura limpa (adaptável ao padrão **MCP** - Model Context Protocol para IA / MVC).

O sistema permite o cadastro detalhado de assistidos (entidades), mapeamento geográfico das residências para cálculo e agendamento de rotas de visitas técnicas, gerenciamento de dependentes e um acompanhamento rigoroso do histórico de interações.

---

## 🎯 Principais Funcionalidades

- **Gestão de Assistidos (Entidades):** Cadastro principal do chefe de família ou indivíduo assistido.
- **Gestão de Dependentes:** Cadastro em banco próprio contendo nome e data de nascimento. A idade é calculada dinamicamente, mantendo relatórios demográficos sempre atualizados.
- **Geolocalização e Rotas:** Os endereços possuem campos de latitude e longitude (preparados para Google Maps API). É possível visualizar a distribuição dos IDs no mapa, criar, editar, deletar e salvar **Rotas de Visitas Técnicas** otimizadas.
- **Histórico e Acompanhamento:** Cada ID possui um registro contínuo (Append-Only) das visitas realizadas. O histórico salva a data, detalhes do atendimento e quem realizou a visita, sem sobrescrever informações passadas.
- **Habilidades (CRUD):** Registro de habilidades profissionais (ex: Manicure, Vendas, Confeitaria, Atendimento) para direcionamento ao mercado de trabalho. Implementado como Entidade (editável dinamicamente via CRUD e pré-populado).

---

## 🛠 Arquitetura e Tecnologias

- **Backend:** Java + Spring Boot (Padrão MVC/REST)
- **Frontend:** React + TypeScript (Previsto)
- **Banco de Dados:** H2 (inicial/desenvolvimento), pronto para migração para PostgreSQL/MySQL.
- **Geolocalização:** Preparado para integração com Google Maps API.
- **Metodologia:** TDD (Desenvolvimento Orientado a Testes).

### Estrutura em Camadas
- `Controller`: Interface de entrada (REST endpoints).
- `Service`: Lógica de negócios.
- `Domain`: Modelagem de entidades.
- `Repository`: Persistência via Spring Data JPA.

---

## 📦 Modelagem de Entidades Principais

```java
public class Entidade {  
    private Long id;  
    private String nome;  
    private String identificador;  
    private String telefone;  
    private Double rendaOuFaturamento;  
    private String status;  
    
    @OneToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "entidade")
    private List<Dependente> dependentes;

    @OneToMany(mappedBy = "entidade")
    private List<RegistroDeInteracao> historicoVisitas;

    @ManyToMany  
    private Set<Habilidade> habilidades;
}   

public class Dependente {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    
    // Idade é calculada em tempo real pela data de nascimento
    @Transient
    private Integer idade;
}

public class Endereco {
    private Long id;
    private String logradouro, bairro, cidade, estado;
    private Double latitude;
    private Double longitude;
}

public class RotaVisita {
    private Long id;
    private String nomeDaRota;
    private LocalDate dataCriacao;
    private String status;
    @OneToMany
    private List<ParadaVisita> paradas;
}
```

---

## 🧪 Metodologia TDD (Test-Driven Development)

Este projeto adota **TDD**. O fluxo de desenvolvimento exige que:
1. **Red**: Escreva testes que falham para uma nova funcionalidade (ex: `DependenteTest` para validar o cálculo de idade).
2. **Green**: Escreva o mínimo de código na classe principal (`Dependente.java`) para fazer o teste passar.
3. **Refactor**: Melhore o código mantendo o teste aprovado.

**Para rodar os testes:**
```bash
./mvnw test
```

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório e navegue até a pasta**.
2. **Execute o script de inicialização:** 
   O projeto conta com scripts auxiliares na pasta `scripts/` para preparação do ambiente de Assistência Social.
   ```bash
   bash scripts/init_social_assistance.sh
   ```
3. **Suba a aplicação Spring Boot:**
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Habilidades Iniciais:**
   Ao iniciar, o banco de dados carregará automaticamente habilidades básicas através do arquivo `src/main/resources/data.sql`.

---

## 📍 Integração de Mapas (Próximos Passos)
O frontend consumirá os `Enderecos` com suas coordenadas para exibir a distribuição espacial das famílias atendidas. Rotas serão traçadas permitindo que as equipes sociais agendem dias de campo eficientes, salvando as `Rotas de Visitas` para uso posterior.
