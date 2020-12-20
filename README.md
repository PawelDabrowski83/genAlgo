# genAlgo

<p>Simple implementation of <a href="https://pl.wikipedia.org/wiki/Algorytm_genetyczny" target="_blank">genetic algorithm</a>. Shared project to practice group collaboration.</p>
<p>Authors: Paweł Dąbrowski, Janusz Brodacki, Kamil Surowiec.</p>

## Goals:
<ul><li>working in small group</li>
<li>practicing git branching, issues and documentation</li>
<li>using TDD</li></ul>

## Development progress
<p>We are working to develop functioning Stage 1 on 21.12.2020</p>

## Stage 1
<p>Creation of Gene class, which is base fundament of our model. Adding com.facebook.genAlgo.RandomProvider interface to test creation of random genes.</p>

> class Gene
> 
> private final com.facebook.genAlgo.RandomProvider
> 
> private char[] values;

![Gene class](images/genAlgo-stage1.jpg)
<p>Gene should have <b>char[] values</b> initialized with random char length and random char values on each element.</p>
