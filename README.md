# mrvn
A discord bot for Apex stat fetching and other general purposes.

# Heroku
View the Heroku dashboard here: https://dashboard.heroku.com/apps/mrvn-discord-bot

There are a ton of useful Herkou commands outlined in the docs https://devcenter.heroku.com/categories/reference, but below you will find some useful ones. These must be run from within the mrvn/ directory:

### Push app
```bash
heroku container:push web --app mrvn-discord-bot
```

### Release app after push
```bash
heroku container:push web --app mrvn-discord-bot
```

### Set environment variable
```bash
heroku config:set <VARIABLE>=<VALUE>
```

### Tail live logs
```bash
heroku logs --tail
```

### Restart chkn
```bash
heroku restart
```
