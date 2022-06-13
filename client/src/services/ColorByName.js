class ColorByName {
    colorByName(name, useTripodColors) {
        return useTripodColors
          ? this.colorByNameTripod(name)
          : this.colorByNameDefault(name);
    }
  
    colorByNameDefault(name) {
        switch (name) {
            case "A":
            case "S":
                return "green";
            case "B":
                return "yellow darken-1";
            case "C":
                return "orange";
            case "D":
            case "Fc":
            case "Fns":
            case "F":
            case "T":
            case "L":
                return "red";
            case "Ac":
                return "green lighten-2";
            case "Bc":
                return "yellow lighten-1";
            case "Cc":
                return "orange lighten-2";
            case "I":
            case "M":
            case "Z":
                return "purple";
            case "U":
            case "W":
            case "X":
                return "grey";
            default:
                return "grey";
        }
    }
  
    colorByNameTripod(name) {
        switch (name) {
            case "A":
            case "B":
            case "C":
            case "S":
                return "green";
            case "Ac":
            case "Bc":
            case "Cc":
            case "Fc":
            case "Fns":
            case "F":
            case "T":
            case "L":
                return "red";
            case "Z":
            case "D":
            case "W":
                return "yellow darken-1";
            case "I":
            case "M":
                return "blue";
            case "U":
            case "X":
                return "grey";
            default:
                return "grey";
        }
    }
}

export default new ColorByName();