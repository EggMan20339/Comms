# Comms

Hello, this is my attempt at a wifi based audio communication system.
the long term goal is to create a 3 channel (or more) audio communication system with at least 4 Beltpacks all that runs off of Raspberry Pis

Server.java (in conjunction with BP.java) is designed to take audio from the Beltpack.java, store it, and send it right back where it came from.

Beltpack.java grabs audio from a microphone and sends that signal to the server, and will then listen for a signal coming from the server.

my current issue is that when i run both Server.java and Beltpack.java in my IDE (Intelij IDEA) the two sides appear to be communicating with eachother. my issue accurs when i
take the Beltpack.java onto a RPi. whenn i do that, it all just seems to break. any ideas? i am new to the Java language and have never delt with any of this stuff before.
