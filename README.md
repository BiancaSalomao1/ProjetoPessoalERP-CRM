# ProjetoPessoalERP-CRM
Projeto para desenvolvimento de habilidades pessoais


# Documentação do Projeto ERP + CRM Genérico

## Visão Geral

Este projeto implementa um sistema ERP + CRM modular, escalável e genérico, capaz de atender diferentes domínios, incluindo gestão social, assistencial, empresarial ou qualquer outro cenário que envolva o cadastro de pessoas físicas, jurídicas e seus relacionamentos.

- **Backend:** Java + Spring Boot
- **Frontend:** React + TypeScript
- **Banco de Dados:** H2 (inicial), com possibilidade de migração para PostgreSQL, MySQL ou outros bancos relacionais.
- **Arquitetura:** Modular, limpa, desacoplada e escalável.

## Arquitetura Backend
**Estrutura em Camadas**
-Controller: Interface de entrada da aplicação, responsável pelos endpoints REST.    
-Service: Contém a lógica de negócio e as regras do domínio.    
-Domain: Modelagem das entidades e regras específicas do domínio.    
-Repository: Persistência dos dados usando Spring Data JPA.    
-Infrastructure: Configurações, segurança, mensageria e integrações externas.    

**Modularização**
-entidade   
-habilidade    
-problemaOuDesafio   
-beneficioOuAuxilio   
-diagnostico   
-planoAcao   
-grupo   
-registroDeInteracao    
-mensagem   
-auth (segurança e autenticação)   
    
_Cada módulo contém suas próprias pastas:   
- controller   
- service      
- domain    
- repository   
- dto    

##Modelagem de Entidades

public class Entidade {  
    @Id @GeneratedValue  
    private Long id;  
    private String nome;  
    private String identificador;  
    private String telefone;  
    private String endereco;  
    private Double rendaOuFaturamento;  
    private Integer quantidadeDePessoasOuFuncionarios;   
    private String status;  
    private String observacoes;  
  
    @ManyToMany  
    private Set<Habilidade> habilidades = new HashSet<>();   
  
    @ManyToMany  
    private Set<ProblemaOuDesafio> desafios = new HashSet<>();   
  
    @ManyToMany   
    private Set<BeneficioOuAuxilio> beneficios = new HashSet<>();   
}   
Habilidade, ProblemaOuDesafio e BeneficioOuAuxilio  
   
public class Habilidade {    
    @Id @GeneratedValue   
    private Long id;   
    private String nome;   
    private String descricao;   
}   
*O mesmo para ProblemaOuDesafio e BeneficioOuAuxilio    
    
public class Diagnostico {    
    @Id @GeneratedValue    
    private Long id;    
    private LocalDate data;    
    private String status;   
    private String resumo;   
   
    @ManyToOne    
    private Entidade entidade;    
}   
    
     
public class PlanoAcao {   
    @Id @GeneratedValue   
    private Long id;   
    private String descricao;   
    private LocalDate prazo;   
    private Boolean cumprido;   
   
    @ManyToOne    
    private Entidade entidade;    
}    
   
public class Grupo {    
    @Id @GeneratedValue    
    private Long id;    
    private String nome;   
    private String descricao;   
    private String tipo;   
   
    @ManyToMany    
    private Set<Entidade> membros = new HashSet<>();    
}    
    
public class RegistroDeInteracao {     
    @Id @GeneratedValue    
    private Long id;     
    private LocalDate data;     
    private String status;    
    private String observacoes;    
    
    @ManyToOne    
    private Entidade entidade;     
}    
    
public class Mensagem {    
    @Id @GeneratedValue    
    private Long id;    
    private String conteudo;    
    private LocalDateTime dataEnvio;    
    private String tipoEnvio;    
    private String status;    
    
    @ManyToOne    
    private Entidade destinatario;     
    @ManyToOne    
    private Grupo grupoDestinatario;   
}    
    
##Banco de Dados    
-Inicialmente com H2 Database.    
-Pronto para migração para PostgreSQL, MySQL, SQL Server ou outros relacionais.    
   
##Segurança   
-Spring Security com autenticação baseada em JWT.    
-Controle de acesso por papéis (RBAC) com os seguintes perfis:    
-Papel	Permissão:     
  ADMIN	Acesso total    
  COORDENADOR	Gestão de grupos, equipes e relatórios     
  AGENTE	Interação direta com entidades    
  CONSULTA	Acesso apenas leitura   
    
##API REST   
-Padrão RESTful.    
-Documentação automática com Springdoc OpenAPI/Swagger.    
-Endpoints organizados por módulo:    

  /api/entidades   

  /api/grupos   

  /api/diagnosticos   

  /api/planoacoes   

  /api/mensagens   


##Arquitetura Frontend (React + TypeScript)
-Estrutura de Pastas :    
src/    
├── components/          
├── features/             
│   ├── entidade/     
│   │   ├── EntidadeList.tsx     
│   │   ├── EntidadeForm.tsx     
│   │   └── entidade.api.ts    
├── hooks/                 
├── lib/                   
├── routes/               
├── types/                
└── App.tsx    

-Ferramentas e Bibliotecas      
React   
React Router    
Axios para comunicação HTTP    
React Query para gerenciar requisições e cache.    
Auth com JWT e controle de rotas protegidas.   

##Escalabilidade e Extensões Futuras   
Pronto para migração para microserviços, se necessário.   
Suporte a mensagens     
Integração com serviços externos (ex.: APIs de WhatsApp, SMS, email).    
Pode ser estendido com dashboards analíticos e relatórios BI.   

## Conclusão  

Este projeto foi desenvolvido para ser genérico, modular e escalável, atendendo tanto organizações assistenciais quanto empresas. Seu núcleo é o cadastro de entidades de forma abstrata e flexível, permitindo expandir funcionalidades conforme a necessidade do domínio de negócio.
