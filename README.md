Matteopplærings-app for barn. Når applikasjonen startes kommer man til et
skjermbilde hvor mulige valg er:
- Start spill
- Om spillet
- Preferanser.

Selve spillet viser addisjonsregnestykker og knapper med tallene 0-9. Man skriver inn svar med knappene.
Regnestykkene og svarene er lagret som arrays i xml-fil under res/values. Det er
15 regnestykker. Regnestykkene vises som random når et spill er startet. Samme
regnestykke kommer ikke opp flere ganger i en sesjon. Standard er at et spill er 5
regnestykker, men dette kan settes til 5, 10 eller 15 i SharedPreferences.

Hensikten er å lære noe av spillet. Det gis gode tilbakemelding til bruker om hva som er
galt/riktig på en konstruktiv og lærerik måte.

Hvis spilleren avbryter spillet før det er ferdig, skal kommer det opp en dialogboks som spør
om det virkelig skal avsluttes. Hvis alle spørsmål er benyttet kommer det en melding til
brukeren om at det ikke er flere tilgjengelige spørsmål.

Målgruppen her er små barn, så mye tekst er unngått siden brukerne kanskje ikke kan lese.

Alle strenger ligger i strings.xml. Ved endring av språk (norsk/tysk) på emulatoren skal alle strenger
i spillet skifte til riktige verdier.

Det ligger en rapport i pdf-format under res/raw folderen. Denne viser
skjermbilder og gangen i applikasjonen + begrunnelse for designvalg som farger,
bruk av ikoner, navigasjonsmuligheter etc.

Prosjektet vil kjøres på en Pixel 9 med API 16.
