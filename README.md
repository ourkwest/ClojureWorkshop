
*** Work In Progress ***
========================

# ClojureWorkshop

A Workshop to get started with Clojure.


## Mildstones*

* Mildstone 1: Read a file from a remote server.
* Mildstone 2: Parse the data out of that file. 
* Mildstone 3: Reformat that data as HTML. 
* Mildstone 4: Serve that HTML over HTTP.

\* A "mildstone" is like a milestone but less intimidating.


## Pre-requisites

0. Install [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
1. Install [Leiningen](http://leiningen.org/)
2. Either run `lein repl` at the command line to start an interactive Clojure prompt or setup an editor to develop with Clojure and then start a REPL from within your editor.
    1. IntelliJ: install 'La Clojure' plugin.
    2. Eclipse: install 'Counterclockwise' plugin.
    3. Emacs: install 'Emacs Live'
    > N.B. Other plugins/editors exist. This tutorial will assume that you are using `lein repl`. 

## Mildstone 1

> Aim: Read a file from a remote server.

To do this we'll need the address of the file, and a Clojure function to read files given their addresses.

The file is located at <http://raw.githubusercontent.com/peterwestmacott/ClojureWorkshop/master/resources/example1.xml>.
The simplest Clojure function to read a file from a URL is called `slurp`.
Clojure functions can be called at the REPL by writing `(function-name arguments...)` in this case `(slurp "http://raw.githubusercontent.com/peterwestmacott/ClojureWorkshop/master/resources/example1.xml")` 


2.	Know about Leiningen:
	1.	Know how to create a leiningen project “lein new <project_name>”
	2.	Know how to add dependencies to a leiningen project [Leiningen Tutorial: Dependencies](https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md#dependencies)
	3.	Know how to start a leiningen repl “lein repl”
3.	Know where the file is located:
	1.	[example1.xml](https://raw.githubusercontent.com/peterwestmacott/ClojureWorkshop/master/resources/example1.xml)
4.	Know some useful Clojure libraries:
	a.	Hiccup can be used as an HTML tempting library
	b.	Ring can be used as a web server
5. 	Know some Clojure:
	a.	Function calls take the form “(function_name arg1 arg2 arg3…)”, can be called at the repl and will always return a value (although that value may be nil).
	b.	“(doc <function_name>)” can be called at the repl to give documentation on any function that is accessible in the current namespace. e.g. “(doc doc)”
	a.	“(def <name> <value>)” can be used to bind a value to a name in the current namespace. This may be useful to capture the return values of functions that you call for later reference in the REPL. e.g. (def my-file-name “http://example.com:80/data.xml”)
	b.	“(slurp <url>)” reads the file at a URL and returns the contents of the file as a String. e.g. (def my-file-content (slurp my-file-name))
	c.	“(use <quoted_namespace_name>)” makes use of another namespace. e.g. (use ‘clojure.xml) [TODO: add examples for hiccup/ring]
	d.	“(clojure.xml/parse <url>)” is the same as slurp but additionally parses XML from the file and returns a Clojure data structure representing the content of the XML file. e.g. (parse my-file-name)
	e.	(Lists look like this), [Vectors look like this], {:Maps look, :like this}, #{Sets look like this}, :keywords :look :like :these
	f.	Lists, Vectors, Maps and Sets all conform to Clojure’s sequence abstraction.
	f.	Keywords are functions that can look up their values in maps. eg. (:datum {:datum 5 :red-herring “Banana”}) would return 5
	g.	“(for [name1 sequence1, name2 sequence2, etc…] <expression>)” evaluates ‘expression’ for every combination of binding every element of sequence1 to name1, binding every element of sequence2 to name2, etc. and returns a sequence of the results. ‘for’ expressions can of course be nested.
	h.	Sequences can also be accessed using the following functions: first, rest, nth.
	i.	Nested maps (and vectors) can be accessed with get-in
	j.	Don’t forget about ‘doc’.

hiccup
ring


5.	Start a REPL
6. 	Write some code
