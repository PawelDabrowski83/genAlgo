# genAlgo

<p>Simple implementation of <a href="https://pl.wikipedia.org/wiki/Algorytm_genetyczny" target="_blank">genetic algorithm</a>. Shared project to practice group collaboration.</p>
<p>Authors: Paweł Dąbrowski, Janusz Brodacki, Kamil Surowiec.</p>

## Goals:
<ul><li>working in small group</li>
<li>practicing git branching, issues and documentation</li>
<li>using TDD</li></ul>

## Development progress
<p>We are working to develop functioning Stage 3 on 04.01.2021 - refactor in Gene and Evaluator.</p>

## Workflow
- We use separate branches to develop each stage of project.
- Each stage consist of at least three steps: documentation, tests and implementation. They are developed in this order and depend on each other.
    - Documentation describes the task.
    - Tests are our specification of product and we want them to describe acceptance conditions for implementation.
    - Implementation is developed at the end of the stage and it should pass all of the tests before release.
- On each stage we put tests before implementation. If during implementation step it appears, that tests are not sufficient, we extend tests before continuing.
- <b>Each task in project is developed based on an Issue</b>. If there is no Issue for your work - create one. Use Issues to report bugs or possible enhancements also.
- When stage is finished, we release it, by pulling current branch to <b>main</b>.
- Each task is developed on separate branch - naming conventions is explained below.

## Branch naming convention
- Production code is release on <b>main</b>
- Stage branches are formed as <b>dev-sX</b>, where X is number of development stage.
- Task branches follow this pattern: <b>X-Label</b>, where X is number of an Issue and Label is short description.

## Stage 1
<p>Creation of Gene class, which is base fundament of our model. Adding RandomProvider interface to test creation of random genes.</p>

## Stage 2
<p>Creation of Evaluator interface and its implementation. </p>

## Stage 3
<p>Documentation refactoring, added new section Code Structure which describe structure of program.</p>
<p>Gene class refactor, gene has field with single char insted of char array.</p>


## Code Structure

> class Gene
> 
> private final RandomProvider
>
> private char value
>
> private void generateValue()

Gene has field char value, generateValue() method use RandomProvider interface to randomly generate char value.

> interface Evaluator
>
> setFitness(Gene)

Evaluator has method setFitness() to calculate fitness of gene
Evaluator count fitness only by comparing two char. One current value in gene with target char
Target char should be passed to Evaluator as argument in constructor

> Formulas for setFitness() method:
>
> variant 1:  1 / (1+log<sub>10</sub>(1+delta))
>
> variant 2: (65535 - delta) / 65535
>
> where:
>
> delta - difference between target and current char
>
> 65535 - value equal to Character.MAX_VALUE

![Code structure](images/genAlgo-stage3.png)


