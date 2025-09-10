Dere skal lage en matteopplærings-app. Når applikasjonen startes skal man komme til et
skjermbilde hvor mulige valg er:
- Start spill
- Om spillet
- Preferanser.

Selve spillet viser addisjonsregnestykker og knapper med tallene 0-9. Man skriver inn svar med knappene.
Regnestykkene og svarene skal lagres som arrays i xml-fil under res/values. Det skal legges
inn 15 regnestykker. Regnestykkene skal vises random når et spill er startet. Samme
regnestykke skal ikke komme opp flere ganger i en sesjon. Standard er at et spill er 5
regnestykker, men dette kan settes til 5, 10 eller 15 i SharedPreferences.

Hensikten er å lære noe av spillet. Det må gis gode tilbakemelding til bruker om hva som er
galt/riktig på en konstruktiv og lærerik måte.

Hvis spilleren avbryter spillet før det er ferdig, skal det komme opp en dialogboks som spør
om det virkelig skal avsluttes. Hvis alle spørsmål er benyttet skal det komme en melding til
brukeren om at det ikke er flere tilgjengelige spørsmål.

Skjermbildet skal benyttes på en så god måte som mulig og designvalg bør følge designregler
fra developer.android.com. Husk å lag eget ikon til appen på desktop og skap gjenkjennelse
gjennom alle skjermbilder.

Målgruppen her er små barn så design bør være rettet mot denne gruppen. Det vil si at man
bør unngå mye tekst siden brukerne kanskje ikke kan lese.

Alle strenger skal ligge i strings.xml. Ved endring av språk (norsk/tysk) på emulatoren skal alle strenger
i spillet skifte til riktige verdier.

Det skal leveres med en rapport som skal ligge under res/raw folderen. Husk at filnavn må ha
små bokstaver. Rapporten skal være i pdf-format navngis med studentnummer og vise
skjermbildene og gangen i applikasjonen deres + begrunnelse for designvalg som farger,
bruk av ikoner, navigasjonsmuligheter etc. Oppgi gjerne kildehenvisning til hvor dere har
funnet opplysninger som støtter at design og navigasjonsvalg er brukervennlige.

Prosjektet vil kjøres på en Pixel 9 med API 16.
