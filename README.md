# xmlidx

A Clojure library designed to index XML files.

## Usage


```clojure
(-> "/path/to/your/file.xml"
      file-xml-events
      index-text-path-simple
      unique-paths)
```

```clojure
(-> "/path.xml"
    file-xml-events
    index-text-path-arrays)
```

## License

Copyright © 2016 Walter C. Pelissero <walter@pelissero.de>

Distributed under the GNU General Public License either version 2.0 or
(at your option) any later version.
