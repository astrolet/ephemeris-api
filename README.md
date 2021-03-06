# ephemeris-api

> Ephemeris HTTP API

This `ephemeris-api` provides [ephemeris](https://github.com/astrolet/ephemeris) functionality, over the HTTP protocol, as a web service.  It is powered by [Pedestal](http://pedestal.io), including adaptors for [Jetty](http://www.eclipse.org/jetty) and [Immutant](http://immutant.org).  Pedestal opens up a "huge variety" of deployment possibilities, not all of which enabled or documented here.  Thanks also go to [pedestal-api](https://github.com/oliyh/pedestal-api) for making it simple and easy.

## Rationale

For the purpose of decoupling astrology applications from ephemeris code.  Therefore one is free to implement an application in any programming language and using a data format of their choice.  There is also the possibility of including and exposing alternative ephemeris implementations with the same interface.  Client applications would simply connect to an api instance configured to serve the ephemeris they want to use / make available to their customers.  Application users with privacy or other concerns (e.g. self-service of low connectivity environments wanting Internet-independent use through local area network, or a fully-offline desktop `localhost`), can easily provide the ephemeris for themselves and for free, as long as their application allows for such configuration of an ephemeris endpoint.

The best or most popular astrology software is generally behind the times with regards to rapid tech industry change in a post-desktop world.  The barrier to entry appears high, costly, even intimidating.  Programming astrology is complicated enough.  An ephemeris should make it easier rather than harder to attract a new generation of hackers, keeping astrology enthusiasm alive.  An ephemeris interface (i.e. its api) should have a relatively small footprint.  Its purpose is to convert one kind of data, i.e. time and place — into another, e.g. positions of planets, stars, a few key points on the ecliptic, and *"testimonies"* come to mind.  A simple non-side-effecting single-purpose transformation function.  Pretty much everything else necessary for the practice of, at least traditional, natal astrology can be derived from this functional ephemeris data by external or third party code libraries.  An ephemeris that aims to do nothing more than the absolute bare minimum is easy to improve or replace.  The same applies to libraries that would transform this data further, into something fit for the practice of astrology, such as a graphical user interface.  Scripted rather than compiled languages can be used for lightweight development.  Poor determined hackers with weak computers can be efficient, successful astrology programmers too.  This almost levels the playing field.

We live in a grey world.  Commercial vs GPL licensing is too black-and-white for a healthy and thriving astrology software ecosystem.  Permissive licenses such as MIT are the popular trend for a reason.  The public domain is on the rise.  People should be free to choose Open Source other than strict copyleft GPL.  The [Unlicense](http://unlicense.org) this organization adopts for its code - takes a strong and clear stance.

By serving the ephemeris api over network protocols and keeping it minimal, we open possibilities for an alternate future of astrology apps.  Any ephemeris-dependent software can ship without an ephemeris and work with any other ephemeris that conforms to a common interface spec.  Ephemeris would be a utility that people can choose to self-provide in the Cloud, on a LAN, or any network with [ZeroTier](https://www.zerotier.com).  That would lower the cost for end-users and app developers alike.  Apps would be leaner and cheaper.  Micro-apps that do very little and are almost free become viable.  Their authors could put such apps on the market without having to afford an ephemeris license, nor the hosting cost of an ephemeris-api, and thus not loose any money if an app fails to summon interest in people.  Even developers from "Third World" countries could compete globally.  If an app does well, it may eventually get an upgrade, perhaps with a more expensive ephemeris-including version.  This is a kind of crowdfunding without asking people for funds in advance.

Furthermore, commercial apps would outlive their authors’ ability to renew their ephemeris license and stick around for the benefit of astrologers even if an ephemeris license is revoked or a specific ephemeris-api service ceases to exist.  Overall, this should pave the way for better astrology software in the small and at large.  Competition wouldn’t have to be monolithic nor “reinventing the wheel” once again.

Remember this is just sharing thoughts about possibilities.  Check for yourself and with a lawyer that whatever course you take and choices you make are all legal.

## Caution

Before you go on, keep in mind there isn’t much here yet.  The `ephemeris` that will be made available is still very incomplete and this isn't even on par with it.  Nevertheless, the course is set and we’ll get there.  Meanwhile, it's a *"work in progress"*…

## Use

Try a demo [api.astrolin.org](http://api.astrolin.org)/.
It has got an automatically-generated, self-documenting ui.

### Development

[OpenJDK](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot) `11` and [Leiningen](https://leiningen.org) `2.4` or greater are prerequisites.

Start `lein repl`, load the `dev` namespace, and call `-main`.
Source code will be auto-reloaded upon changes for easier dev,
thanks to [ns-tracker](https://github.com/weavejester/ns-tracker).

Running `lein repl` will take you to `ns dev` by default -- then:

```clojure
(-main)
```

### Standalone Package

```sh
lein do clean, uberjar
java -Dnomad.env=prod -jar target/server.jar
```

### Cofiguration

There are [nomad](https://github.com/jarohen/nomad) defaults pre-configured per environment in `resources/config.edn` that can be overridden in a number of ways.  Currently either through environment variables or Java system properties.  For example the following two commands will do the same:

- `  EPHEMERIS_API_PORT=8080 java -jar target/server.jar`
- `java -Dephemeris.api.port=8080 -jar target/server.jar`

If you use both approaches together, e.g. `EPHEMERIS_API_PORT=8080 java -Dephemeris.api.port=8081 -jar target/server.jar`, then the second one wins and the port would be `8081`.

See [environ](https://github.com/weavejester/environ#readme)’s documentation for more info / possibilities.  The following vars, or their `java`-option equivalents, will be used if provided:

* `EPHEMERIS_API_EVER` can make api version be different from `project.clj`'s
* `EPHEMERIS_API_BASE` the path where the api is served, e.g. `/`
* `EPHEMERIS_API_TYPE` = `jetty` or `immutant`
* `EPHEMERIS_API_HOST`
* `EPHEMERIS_API_PORT`

## Deploy

There are many ways one can deploy the api, to Cloud platforms being many.
Here are some that have been tried, mostly for their convenience and free tier.

### Heroku

Host your own Ephemeris API server for **FREE** on [Heroku](https://heroku.com) with [One-Click Deploy](https://heroku.com/deploy?template=https://github.com/astrolin/ephemeris-api/tree/master).

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/astrolin/ephemeris-api/tree/master)

Already on Heroku at [ephemeris.herokuapp.com](https://ephemeris.herokuapp.com) with a free instance -
that is automatically deployed from the `active` branch and used for testing as [test.astrolin.org](http://test.astrolin.org).
It could be broken on occasion, as *work in progress*.

### Bluemix

[IBM Bluemix](https://www.ibm.com/cloud-computing/bluemix) also offers 512 MB for FREE, except that your app won't fall aseep due to inactivity.
You may need a slightly different [manifest](https://github.com/astrolin/ephemeris-api/blob/active/manifest.yml).

### OpenShift

[OpenShift Origin](https://www.openshift.org) is interesting for its [WildFly](http://wildfly.org) offering, which is especially well-suited for [Immutant](http://immutant.org), our default production adapter type, all of these being backed by RedHat.  The `lein immutant war` here for this reason hasn't been deployed successfully yet.  At the time of this writing the platform is still in preview mode with that has to be renewed each month, as accounts are forced to expire.  It'd be great to eventially verify it working.

## License

[Unlicensed](http://unlicense.org) free and unencumbered public domain software.

[Ephemeris LICENSE](https://github.com/astrolet/ephemeris/blob/active/LICENSE)
applies in order to use the api - which currently means:
[Swiss Ephemeris License](http://www.astro.com/swisseph).
