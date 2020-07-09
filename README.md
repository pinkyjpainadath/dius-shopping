## DiUS Shopping Checkout Application

## Requirement:
https://raw.githubusercontent.com/DiUS/coding-tests/master/dius_shopping.md

## Pre-requisites for running the Application:
Java 1.8 and maven


## Main Features
- Pricing Rules are implemented as different strategies and made configurable . New rules can be extended in the same way.
- Test included

## Modules
- Application.java = Main entry class
- rules = different strategies for pricing rules captured here
- model = domain model
- exception = custom exceptions captured here
- resources = discount configuration for each sku captured here
- test = unit testcases

## Assumption
- No GUI or command line required
- Best practices like logging, database integrations etc not included
- MVP Solution takes assumption that each product will have one offer attached to it

## Building Application:
To build, call mvn install

## Running Test:
To run the unit tests, call mvn test

