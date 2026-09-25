static void main() {
    long[] platser = new long[20];
    Meny(platser);
}

static void Meny(long[] platser) {
    boolean isRunning = true;
    while (isRunning) {
        IO.println("\n1. köp biljett\n2. lediga platser\n3. omsätning\n4. avboka \n5. avsluta");
        int Val = StringToInt("Val: ");
        switch (Val) { // jag vet att man kan göra switches med strings men det ser fult ut
            case 1 -> Bokning(platser);
            case 2 -> {
                LedigPlatser(platser);
                IO.readln("tryck enter för att fortsätta");
            }
            case 3 -> Omsätning(platser);
            case 4 -> Avboka(platser);
            case 5 -> isRunning = false;
            default -> IO.println("Du har bara fem val är du blind eller?");
        }
    }
}

static void Bokning(long[] platser) {
    boolean ledigPlats = LedigPlatser(platser);
    if (ledigPlats) {
        int val;
        while (true) {
            val = StringToInt("vilken plats vill du boka: ") - 1;
            if (val < 0 || val > 19) {
                IO.println("Du kan bara välja mellan 1 och 20");
            } else {
                if (platser[val] == 0) {
                    while (true) {
                        long PersonnummerCheck = StringToLong("Personnummer (ÅÅÅÅMMDDXXXX): ");
                        if (PersonnummerCheck >= 100000000000L && PersonnummerCheck <= 999999999999L) {
                            platser[val] = PersonnummerCheck;
                            IO.println("plats " + (val + 1L) + " är bokad till " + platser[val]);
                            IO.readln("Tryck enter för att fortsätta");
                            break;
                        } else {
                            IO.println("Du måste använda tolvsifrikt personnummer (ÅÅÅÅMMDDXXXX)");
                        }
                    }
                    break;
                } else {
                    IO.println("Platsen redan tagen du får välja en annan plats");
                    IO.println("De här platserna är lediga: ");
                    LedigPlatser(platser);
                }
            }
        }
    } else {
        IO.println("Det finns inga platser");
    }
}

static void PersonnummerTillPlats(long[] platser) {
    long personnummer = StringToLong("Vad är ditt personnummer: ");
    for (int index = 0; index < platser.length; index++) {
        if (platser[index] == personnummer) {
            IO.println("Ja du har plats " + index + 1);
        } else {
            IO.println("tyvärr Personnumret " + personnummer + " har ingen registrerad plats");
        }
    }
    IO.readln("tryck enter för att fortsäta");
}

static boolean LedigPlatser(long[] platser) {
    boolean ledigPlats = false;
    int platsNummer = 0;
    for (long plats : platser) {
        platsNummer++;
        if (plats == 0) {
            ledigPlats = true;
            IO.print(platsNummer);
            FönsterPlatser(platsNummer);
            IO.print(", ");
        }
    }
    IO.println("är lediga");
    return ledigPlats;
}
static void FönsterPlatser(long plats){
    if (0 == (int)(plats % 4) || 1 == (int)(plats % 4)){
        IO.print(" (förnster plats)");
    }
}
static void Omsätning(long[] platser) {
    int tjänadePengar = 0;
    for (long plats : platser) {
        if (plats <= 200809280000L || plats != 0) {
            tjänadePengar += 300;
        }
        else if (plats > 200809280000L){
            tjänadePengar += 150;
        }

    }
    IO.readln("Du har tjänat " + tjänadePengar + "kr.\ntryck enter för att fortsätta");
}

static void Avboka(long[] platser) {
    boolean platsHittad = false;
    long personnummer = StringToLong("Vilket personnummer: ");
    for (int index = 0; index < platser.length; index++) {
        if (personnummer == platser[index]) {
            platsHittad = true;
            platser[index] = 0L;
            IO.readln("Plats " + index + 1L + " är nu inte längre bookad till " + personnummer
                    + "\nTryck enter för att fortsätta");
        }
    }
    if (!platsHittad) {
        IO.readln("Det finns ingen plats kopplad till" + personnummer + "\nTryck enter för att forsätta");
    }
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

static Long StringToLong(String s_number) {
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