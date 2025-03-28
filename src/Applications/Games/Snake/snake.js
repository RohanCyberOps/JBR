function start() {
    // Define choices
    const choices = ['s', 'w', 'g'];

    // Function to get computer's choice
    function getComputerChoice() {
        const randomIndex = Math.floor(Math.random() * choices.length);
        return choices[randomIndex];
    }

    // Function to determine the winner
    function determineWinner(userChoice, computerChoice) {
        if (userChoice === computerChoice) {
            return "It's a tie!";
        } else if (
            (userChoice === 's' && computerChoice === 'w') ||
            (userChoice === 'w' && computerChoice === 'g') ||
            (userChoice === 'g' && computerChoice === 's')
        ) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }

    // Main game loop
    let playAgain = true;
    while (playAgain) {
        // Get user choice
        let userChoice = prompt("Enter 's' for Snake, 'w' for Water, or 'g' for Gun:");
        if (!choices.includes(userChoice)) {
            alert("Invalid input. Please enter 's', 'w', or 'g'.");
            continue;
        }

        // Get computer choice
        const computerChoice = getComputerChoice();

        // Determine winner
        const result = determineWinner(userChoice, computerChoice);

        // Show result
        alert(You chose: ${userChoice}\nComputer chose: ${computerChoice}\nResult: ${result});

        // Ask to play again
        playAgain = confirm("Do you want to play again?");
    }
}