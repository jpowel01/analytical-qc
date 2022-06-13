class ColorByGrade {
    colorByGrade(name, useTripodColors) {
        return useTripodColors
          ? this.colorByGradeTripod(name)
          : this.colorByGradeDefault(name);
    }
  
    colorByGradeDefault(grade) {
        switch (grade) {
            case "A":
                return "green";
            case "B":
                return "yellow darken-1";
            case "C":
                return "orange";
            case "D":
            case "Fc":
            case "Fns":
            case "F":
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
                return "grey";
            default:
                return "grey";
        }
    }
  
    colorByGradeTripod(grade) {
        switch (grade) {
            case "A":
            case "B":
            case "C":
                return "green";
            case "Ac":
            case "Bc":
            case "Cc":
            case "Fc":
            case "Fns":
            case "F":
                return "red";
            case "Z":
            case "D":
            case "W":
                return "yellow darken-1";
            case "I":
            case "M":
                return "blue";
            case "U":
                return "grey";
            default:
                return "grey";
        }
    }
}

export default new ColorByGrade();