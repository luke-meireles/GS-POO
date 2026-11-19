# Territory Predictive Insights

Global Solution 2º/2026 de Programação Orientada a Objetos (FIAP), tema **CitizenTech**.

**Recorte:** clima local, com chuva intensa e risco de alagamento em pontos vulneráveis.

**Integrante:** 
- _Lucas Gabriel Alvarenga e Meireles_,_RM_  _567305_; 
- _Leonardo Kenji Kubo Barboza,_ _RM  567518_

 O sistema simula as leituras de três sensores instalados num bairro (chuva, nível do rio e umidade do solo), faz previsões para as próximas leituras e emite alertas quando a previsão se aproxima ou passa dos limites definidos para o território.

## Como compilar e rodar

**Requisito:** Java 17 ou superior.

Na pasta do projeto, compile:

```bash
javac *.java
```

E rode:

```bash
java SistemaMonitoramento
```

**Código de acesso para alterar os limites de alerta (opção 4 do menu):** `CIVIL2026`

### Roteiro sugerido para testar

1. **Opção 3** logo ao abrir: as previsões aparecem como "dados insuficientes", porque ainda não há leituras.
2. **Opção 2**, simulando **6 rodadas** ou mais. As leituras são aleatórias, dentro de faixas realistas para cada sensor.
3. **Opção 3** de novo: as duas estratégias fazem a previsão, e os alertas aparecem com o nível e o motivo.
4. **Opção 4**: tente alterar um limite com o código errado e depois com o código certo.
5. **Opção 5**: mostra o status completo do território, dos sensores e das estratégias.

### Observações

- No menu, digite **apenas números**.
- Nos limites com casas decimais, use **vírgula** (ex.: `3,5`). Se der erro, tente com ponto (`3.5`), porque isso depende da configuração do computador.
- Como as leituras são aleatórias, os alertas mudam a cada execução. Se tudo aparecer como NORMAL, simule mais algumas rodadas.

## Estrutura do projeto

| Arquivo                                                               | Papel                                                                        | Conceito de POO                         |
| --------------------------------------------------------------------- | ---------------------------------------------------------------------------- | --------------------------------------- |
| `ComponenteTerritorial.java`                                        | Base de todo componente instalado no território (id, nome, região, status) | Classe abstrata                         |
| `Sensor.java`                                                       | Contrato que todo sensor cumpre                                              | Interface                               |
| `SensorChuva.java`, `SensorNivelRio.java`, `SensorUmidade.java` | Os três sensores                                                            | Herança + implementação de interface |
| `DadosTerritorio.java`                                              | Região, população e limites de alerta protegidos por código              | Encapsulamento                          |
| `EstrategiaPrevisao.java`                                           | Base das formas de previsão                                                 | Classe abstrata                         |
| `PrevisaoMediaMovel.java`, `PrevisaoComparacao.java`              | As duas formas de prever                                                     | Herança +`super`                     |
| `SistemaMonitoramento.java`                                         | Menu, simulação e alertas                                                  | Classe principal                        |

## Público beneficiado

> **Quem sofre o problema?** Quem é atingido quando o bairro alaga?

_Os moradores de areas mais baixas do bairro e das casas de beira do corrego, pois são os primeiros a serem atingidos quando a agua sobe. vale também por um adendo sobre pessoas que sofrem por baixa mobilidade._

> **Qual decisão o sistema apoia?** O que a associação de bairro ou a Defesa Civil consegue decidir com os alertas?

_A decisão que o sistema apoia é de agir antes do alagamento acontecer. Ele avisa os moradores e aciona a Defesa Civil._

## Decisões da equipe

| Decisão                               | Valor                                                                                |
| -------------------------------------- | ------------------------------------------------------------------------------------ |
| Território (fictício)                | Vila Ribeirinha, 12.500 habitantes                                                   |
| Faixa simulada da chuva                | 0 a 50 mm/h                                                                          |
| Faixa simulada do nível do rio        | 0,5 a 5,0 m                                                                          |
| Faixa simulada da umidade do solo      | 30 a 100 %                                                                           |
| Limites iniciais de alerta             | Chuva 30 mm/h, rio 3,5 m, umidade 85 %                                               |
| Faixas aceitas para alterar os limites | Chuva 5 a 100, rio 0,5 a 7,5, umidade 30 a 100                                       |
| Previsão por média móvel            | Média das últimas 5 leituras                                                       |
| Previsão por comparação             | Compara a média dos 3 últimos períodos com os 3 anteriores e projeta a tendência |
| Níveis de alerta                      | ATENÇÃO a partir de 80 % do limite, ALERTA acima de 100 %, CRÍTICO acima de 130 % |

> **Por que essas escolhas?** Explique com suas palavras pelo menos: por que os sensores herdam de `ComponenteTerritorial` e implementam `Sensor`; por que o sistema não faz previsão com poucas leituras; e por que os limites ficam protegidos por código.

_Porque todo sensor é um componente do territorio (tem id,nome,região e status) e todo sensor precisa ter os mesmos métodos de leitura, garantidos pelo contrato da inferface._

_Porque com menos leituras a previsão não fica confiavel._

_Para que ninguem sem autorização altere um limite e impeça que os alertas disparem._

## Reflexão crítica

> **Quando o sistema deve se calar?** Com menos de 5 leituras, a previsão é confiável? E quando o sensor está desativado?

_QUando ha menos leituras que o minimo de cada estrategia e quando o sensor está desativado._ 

_Não, com poucas leituras a previsão é modificada, alterando a media, ela deixa de ser previsão e vira chute._

_O sistema deve se calar do mesmo jeito. O sensor não está lendo, então a previsão usaria leituras antigas, então são imprecisas_

> **O que os dados não representam?** Um sensor por região inteira diz o quê sobre um ponto específico? O que os sensores não enxergam?

_Um sensor mede um unico ponto, então não representa o que acontece em cada rua da região. também não vê causas como bueiro entupido._

_ele diz pouco, ele mede só o local onde está instalado, e outro ponto do bairro pode estar diferente._

_Problemas como bueiro entupido como citado antes, lixo no corrego e coisas como quem precisa de mais tempo para evacuar_

> **Por que o alerta apoia a decisão da pessoa, em vez de decidir por ela?**

_Porque o sistema Só enxera numeros, e quem conhce a região e responde pela decisão é a pessoa. O alerta mostra o que ela precisa para avaliar antes de agir._

## O que faltou e como continuaria

- Se a pessoa digitar uma letra onde o menu espera um número, o programa é encerrado com erro. Isso seria resolvido com tratamento de exceções (`try/catch`), que ainda não foi estudado.
- A previsão por comparação pode passar do valor fisicamente possível (por exemplo, umidade acima de 100 %). Isso não afeta os alertas, mas uma próxima versão poderia limitar a previsão ao máximo de cada sensor.
- As leituras são simuladas com `Math.random()`. Uma próxima versão poderia usar dados reais de uma estação meteorológica (como o INMET).
- O bônus (histórico em arquivo ou simulação de 24 horas) não foi implementado.

## Divisão de tarefas

_Lucas Gabriel - Codigo e Readme.
Leonardo Kenji - Revisão, teste._

## Uso de IA

_O projeto teve o uso de IA durante o processo. A IA foi usada como Tutor, mais expecificamente: analizando, explicando e corrigindo o codigo. Ela não foi usada como coder do projeto._
