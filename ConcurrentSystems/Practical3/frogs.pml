int frogs[5];

init {
    frogs[0] = 3;
    frogs[1] = 1;
    frogs[2] = 2;
    frogs[3] = 4;
    frogs[4] = 5;    
    print_frogs();
    run move_frog(1);
    run move_frog(2);
    run move_frog(3);
    run move_frog(4);
    (_nr_pr == 1)
    printf("DONE!\n");
}

proctype move_frog(int index){
    int temp;
    if
    ::(index < 3) -> printf("START FROG %d AT %d GOING RIGHT\n", index, frogs[index]);
    ::(index >= 3) -> printf("START FROG %d AT %d GOING LEFT\n", index, frogs[index]);
    fi;
    do
    ::atomic {
        if
        ::(index >= 3 && ((frogs[index] - frogs[0] ==2 ) || (frogs[index] - frogs[0] == 1))) ->
            temp = frogs[0];
            frogs[0] = frogs[index];
            frogs[index] = temp;
            printf("MOVE FROG%d FROM %d TO %d\n", index, frogs[0], frogs[index]);
            print_frogs();
        ::(index <3 && ((frogs[0] - frogs[index] == 2) || (frogs[0] - frogs[index] == 1))) ->
            temp = frogs[0];
            frogs[0] = frogs[index];
            frogs[index] = temp;
            printf("MOVE FROG%d FROM %d TO %d\n", index, frogs[0], frogs[index]);
            print_frogs();
        fi; 
    }
    ::atomic {
        (frogs[1] + frogs[2] == 9 && frogs[3] + frogs[4] == 3) 
        break;
    }
    od;
}

inline print_frogs() {
    printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0], frogs[1], frogs[2], frogs[3],f rogs[4]);
}
