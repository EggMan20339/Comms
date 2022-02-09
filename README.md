# Comms

Hello, this is my attempt at a wifi based audio communication system.
the long term goal is to create a 3 channel (or more) audio communication system with at least 4 Beltpacks

Server.java (in conjunction with BP.java) is designed to take audio from the Beltpack.java, store it, and send it right back where it came from.

Beltpack.java grabs audio from a microphone and sends that signal to the server, and will then listen for a signal coming from the server.
