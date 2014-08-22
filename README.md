
*** Work In Progress ***
========================

# Clojure Workshop

A Workshop to get started with Clojure. We will perform some simple tasks to build familiarity with Clojure development.


## Mildstones

* Mildstone 1: Read a file from a remote server.
* Mildstone 2: Parse the data out of that file.
* Mildstone 3: Reformat that data as HTML.
* Mildstone 4: Serve that HTML over HTTP.

\* A "mildstone" is like a milestone but less intimidating.


## Pre-requisites

0. Some basic computer science knowledge. This is aimed at people who already have some familiarity with at least one programming language.
1. Install [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. Install [Leiningen](http://leiningen.org/)
3. Either run `lein repl` at the command line to start an interactive Clojure prompt or setup an editor to develop with Clojure and then start a REPL from within your editor.
    1. IntelliJ: install 'La Clojure' plugin.
    2. Eclipse: install 'Counterclockwise' plugin.
    3. Emacs: install 'Emacs Live'

> N.B. Other plugins/editors exist. This tutorial will assume that you are running `lein repl` at the command line.


## Mildstone 1

> Aim: Read a file from a remote server.

To do this we'll need the address of the file, and a Clojure function to read files given their addresses.

The file is located at <https://raw.githubusercontent.com/peterwestmacott/ClojureWorkshop/master/resources/example1.xml>. (There are also example files 2 and 3 if you prefer.)
As this is a bit unwieldy we can save this to a 'var' by calling `def` so that we can refer to it later more succinctly.

Clojure functions can be called at the REPL by writing `(function-name arguments...)` in this case `(def file-url "http://raw.githubusercontent.com/peterwestmacott/ClojureWorkshop/master/resources/example1.xml")`.

Type (or copy + paste) this into your REPL and hit return.
You have now defined a mapping in your namespace between the name 'file-url' and a String representing the file URL.

The simplest Clojure function to read a file from a URL is called `slurp`.
You can now call this as follows: `(slurp file-url)`
This will fetch the file's contents from the given URL and display them to you.

To make this more readable you can wrap the call to `slurp` in a call to `println` thus: `(println (slurp file-url))`

> Congratulations! (Assuming that worked?) You successfully fetched a remote file and have completed the first Mildstone.


## Mildstone 2

> Aim: Parse the data out of that file.

The more observant amongst you may already have noticed that the data in the example files are in XML form.
While XML is a reasonably transparent format we still need to parse the raw text into more manageable data structures so that we can manipulate it more easily.

Luckily there is a Clojure function for parsing XML in the `clojure.xml` namespace called `parse` that does exactly this.
To make functions from another namespace available you can call the `use` function with the quoted name of the namespace as follows: `(use 'clojure.xml)`.
You can then call `parse` as if it were a function in your namespace. In our example it can be used as a drop-in replacement for `slurp`.

If in doubt about how to use a function you can try calling `doc` on it, e.g. `(doc parse)`. Alternatively you can use your search engine of choice.

If you call `parse` on the `file-url` you should see a representation of the data that is in the XML file.
The format of this data may look unfamiliar, but as we shall see we can manipulate it with ease.

> Congratulations! (Assuming that worked?) You successfully fetched and parsed a remote file into native Clojure data structures.


## Mildstone 3

> Aim: Reformat that data as HTML.

We now have access to the data as a Clojure data structure - but what exactly is that and, more importantly, what can we do with it?

Firstly, lets bind it to a var using `def` as we did before.
I expect you can work out how to do this yourself by now.
I'm going to assume you called your new var `root` as it represents the root element of the XML document.
We can call `pprint` on `root` to pretty print this data structure.
You should see a pair of curly braces ("{...}") with a bunch of stuff in pairs in between.
This means that `root` refers to a map.

### Working with maps

A map in Clojure represents a 1-to-1 mapping from a number of unique keys to a number of other values.
Idiomatically, the keys in a Clojure map are keywords, although this is not always true.
Keywords are prefixed with a colon, for example: `:keyword`, and can be called as functions taking a map as an argument and returning their associated value from the map.

Try calling `:tag` on `root`. This should return another keyword which is a value from the map and represents the tag-name of the root element of the XML document.

Alternatively we can call `:content` on `root` to get the content of the root element.
Let's bind this to another var called `vehicles` as that's what the content of this XML represents.
If you call `pprint` on `vehicles` you should see a pair of square brackets ("[...]") with a bunch of stuff in between.
This means that `vehicles` refers to a vector, which is a kind of sequence.

### Working with sequences

The two most basic operations that you can perform with a sequence are `first` and `rest`.
Try calling these functions on `vehicles` to see what they do. Alternatively try calling `doc` on `first` and `rest`.

Another powerful technique is to call `map` on a sequence.
If you call `doc` on `map` you may come to understand that `map` takes another function as its first argument followed by any number of sequences.
When executed `map` will pull items from the given sequences and use them as arguments to a series of calls to the given function.

For example, let's say we called `(map :tag vehicles)`, this would return the result of calling `:tag` on each element in the `vehicles` sequence.
In other words, this gives us the tag-name of every element that was in the root element of the XML document. We could also use `map` with `:content`.





2.	Know about Leiningen:
	1.	Know how to create a leiningen project “lein new <project_name>”
	2.	Know how to add dependencies to a leiningen project [Leiningen Tutorial: Dependencies](https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md#dependencies)
	3.	Know how to start a leiningen repl “lein repl”

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
