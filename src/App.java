static void main() {
    long[] platser = new long[20];
    Meny(platser);
}

static void Meny(long[] platser) {
    boolean isRunning = true;
    while (isRunning) {
        IO.println("1. Köp biljett\n2. lediga platser\n3. omsätning\n4. avsluta");
        int Val = StringToInt("Val: ");
        switch (Val) { // jag vet att man kan göra switches med strings men det ser fult ut
            case 1 -> Bokning(platser);
            case 2 -> Platser(platser);
            case 3 -> Omsätning();
            case 4 -> isRunning = false;
            default -> IO.println("Du har bara fyra val är du blind eller?");
        }
    }
}

static void Bokning(long[] platser) {
    boolean ledigPlats = Platser(platser);
    if (ledigPlats) {
        int val;
        while (true) {
            val = StringToInt("vilken plats vill du boka: ") - 1;
            if (val < 0 || val > 19){IO.println("Du kan bara välja mellan 1 och 20");}
            else{
                if (platser[val] == 0){
                    while (true)
                    {
                        long PersonnummerCheck = StringToLong("Personnummer (ÅÅÅÅMMDDXXXX): ");
                        if (PersonnummerCheck >= 100000000000L && PersonnummerCheck <= 999999999999L)
                        {
                            platser[val] = PersonnummerCheck;
                            IO.println("plats " + (val + 1L) + " är bokad till " + platser[val]);
                            IO.readln("Tryck enter för att fortsätta");
                            break;
                        }
                        else{
                            IO.println("Du måste använda tolvsifrikt personnummer (ÅÅÅÅMMDDXXXX)");
                        }
                    }
                    break;
                }
                else{
                    IO.println("Platsen redan tagen du får välja en annan plats");
                    IO.println("De här platserna är lediga: ");
                    Platser(platser);
                }
            }
        }
    }
    else{
        IO.println("Det finns inga platser");
    }
}

static boolean Platser(long[] platser) {
    boolean ledigPlats = false;
    int platsNummer = 0;
    for (long plats : platser) {
        platsNummer++;
        if (plats == 0) {
            ledigPlats = true;
            IO.print(platsNummer + ", ");
        }
    }
    IO.println("är lediga");
    return ledigPlats;
}

static void Omsätning() {

}

static int StringToInt(String s_number) {
    boolean temp = true;
    int number = 1;
    while (temp) {
        try {
            number = Integer.parseInt(IO.readln(s_number));
            temp = false;
        } catch (Exception e) {
            IO.println("Skriv inga bokstäver eller något dumt");
        }
    }
    return (number);
}
static Long StringToLong(String s_number)
{
    boolean temp = true;
    long number = 1L;
    while (temp) {
        try {
            number = Long.parseLong(IO.readln(s_number));
            temp = false;
        } catch (Exception e) {
            IO.println("Skriv inga bokstäver eller något dumt");
        }
    }
    return (number);
}