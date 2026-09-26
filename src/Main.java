void main() {

    try {
        Program program = new Program();
        program.Run();

    } catch (Exception e) {
        System.err.println("Noe gikk galt: " + e.getMessage());
        e.printStackTrace();
    }
}