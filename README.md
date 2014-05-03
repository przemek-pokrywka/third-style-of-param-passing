## What is ThirdStyle and why does it matter?

ThirdStyle is a particular style of parameter passing in an application. It's not an official name. Actually I don't think any official name for it exist. Because two other styles dominate software ([LongParameterList](http://c2.com/cgi/wiki?LongParameterList) and [ParameterObject](http://c2.com/cgi/wiki?ParameterObject), which I call "plain" and "context object" respectively), I arbitratily chose to call it the third.
The best place I know that describes the style is [@mhevery's](https://twitter.com/mhevery) [blog post](http://misko.hevery.com/2009/04/15/managing-object-lifetimes/).

The style can be characterized by:

1. very strict adherence to Law of Demeter (data is passed only where it is actually needed)
2. less work in unit testing, refactoring and merging
3. it can be directly derived from best practices of object oriented programming

Using it lets you with less code, less work, and the code that remains stays cleaner. 

## Talk is cheap, prove it!

The main goal of that repository is to prove assertions stated above.
It lets you track evolution of an example "hello-world"-type project in three parallel branches:

1. plain - where the parameters required at some deep level of the system are passed from the top often through many layers. Here you'll certainly find [LongParameterLists](http://c2.com/cgi/wiki?LongParameterList).
2. context - slight improvement over the plain method, where whole group of parameters is wrapped into a context object ([ParameterObject](http://c2.com/cgi/wiki?ParameterObject)) and passed through the layers as one.
3. shy - the third style example, where data is passed just where needed, like in [@mhevery's](https://twitter.com/mhevery) [blog post](http://misko.hevery.com/2009/04/15/managing-object-lifetimes/).

Each branch (aside of initial refactoring commit) contains exactly the same features added in the same order.
By comparing the resulting code you can get the idea what works better - where the diffs and merge conflicts are smaller and where codebase is smaller and cleaner (less coupled and more cohesive).
For even better results, checkout some of the git tags and try to implement/merge some features yourself in different approaches. Nothing teaches better than the direct experience of pain stemming from bad code structure.

## So what's next?

1. Get the overview of code history using 'git log --graph --all --pretty=format:"%Cgreen%h%Creset %s %Cgreen%d%Creset"' or using your favourite tool.
2. Select an interesting place (before some merge, or when some feature is completed).
3. Jump there using "git checkout nameOfTagOrBranch".
4. Try out doing the merge yourself "git merge branch1 branch2" or see what changed since the last revision "git diff HEAD~1".
5. Find analogous place on a different branch and repeat steps 3 and 4.
6. Repeat until no more interesting places are left.

You should see, that amount of work to implement the same features is smaller on the "shy" branch.
At that point you are ready to practice the style in your code and to spread the word. Happy coding!

