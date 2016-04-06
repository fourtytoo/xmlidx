# xmlidx

A Clojure library designed to index XML files.

## Usage

Add the dependency to your lein project.clj

```clojure
[fourtytoo/demyjtify "0.1.0-SNAPSHOT"]
```

Require the library in your namespace

```clojure
(ns my-program.core
  (:require [xmlidx.core :as xmlidx]))
```

Index a file

```clojure
(-> "/path/to/your/file.xml"
     idx:file-xml-events
     idx:index-text-path-simple
     frequencies)
```

or if you need each element repetitions; for instance for when you
need to index them one by one with an xpath:

```clojure
(-> "/path/to/another/file.xml"
    idx:file-xml-events
    idx:index-text-path-arrays)
```

## License

Copyright © 2016 Walter C. Pelissero <walter@pelissero.de>

Distributed under the GNU General Public License either version 2.0 or
(at your option) any later version.
