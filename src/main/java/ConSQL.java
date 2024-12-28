import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConSQL {
    public static void main(String[] args) {
        try {
            // URL de votre fichier PHP
            String urlString = "https://dev-henin.com/serenity/get_table.php"; // Remplacez par l'URL de votre fichier PHP

            // Créer un objet URL à partir de l'URL du fichier PHP
            URL url = new URL(urlString);

            // Ouvrir la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Vérifier le code de réponse HTTP
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                // Lire la réponse
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Afficher la réponse (c'est du JSON)
                System.out.println("Réponse du serveur : ");
                System.out.println(response.toString());
            } else {
                System.out.println("Erreur HTTP : " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
