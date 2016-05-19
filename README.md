# pnc-eclipse-plugin
Eclipse plugin to interact with DA and PNC

Purpose of the plugin is to connect to the DA and PNC services, query them for dependencies and eventually trigger a build of a new dependency version (if required).
This way user can have his pom.xml analyzed and aligned his dependencies properly.

The initial plugin implementation was done as part of Denis Richtarik's [bachelor thesis](https://diplomky.redhat.com/thesis/show/391/eclipse-plugin-for-triggering-project-newcastle-workflows).
