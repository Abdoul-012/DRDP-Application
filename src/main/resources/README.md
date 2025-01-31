
1- astuce pour inclure des pages
    declaration du thymlyfe
        xmlns:th="http://www.thymeleaf.org"
        declaration du fragment
        <th :fragment=""xx">
        inclusion
        <th: replace ="xxx :: yy">
2- astuce pour inclure le css
    <link rel="stylesheet" th:href="@{/styles/footer.css}">
     pour eviter les erreurs il est preferable dinclure les fichier css 
    dans les pages principales
        
3- Avec thymeleaf, il est plus simple de lier le formulaire a un objet pour eviter les erreur
    par exemple, j'ai une entite Utilisateur, dans mon controller je cree un objet de type utilisateur
    je linjecte dans le formulaire avec "org.springframework.ui.Model" ensuite dans le form 
    je fais un th:object="${utilisateur}" puis je lie chaque champs du formulaire a un attribut
    th:field=" *{nom}"
4- ne jamais stocker le mdp en claire , je le hash avec PasswordEncoder que je declare dans la classe 
    SecurityConfig ensuite je le delcare dans la classe Service
5- et aussi chose tres importante tjrs mettre le clef primaire en ID generation et pas identity ca peut creer des problem

6- SPRING SECURITE POUES LES AUTORISATIONS
        la classe doit zvoir en entete
        @Configuration
        @EnableWebSecurity
dans la lasse je cree un bean: une methode qui va returner un SecurityFilterChain qui prend 
paramaetre HttpSecuity fournit par SpringSecurity
  HttpSecurity.
                csrf(AbstractHttpConfuiguer :: disable) pour descater les requete de port different
                .authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/xxx").permitALL()
//hasRole(""") 
//hasAnyRole()
                .anyRequest().authenticate()
).build

6- SPRING SECURITE POUES L'AUTHENTIFICATION
    un beans qui return un InMemoryDetailsManager ()

    
8- une bonne pratique pour lauhtentification
je recupere lutilisateyur en fonction de lid ensuite je compare le mot de passe dans la bd avec 
avec celui recuperer 

9 - Astuce java Scripte pour les alerts avec sweatalert
 a- je declare les conteneur avec les message charger dynamiquement
    <div th:if="${error}" style="display: none;" id="error-container" th:text="${error}"></div>
    <div th:if="${succes}" style="display: none;" id="succes-container" th:text="${succes}"></div>

 b-je declare le script

    document.addEventListener("DOMContentLoaded", function () { -> jajoute levenement
        const messageContainer = document.getElementById("succes-container"); -> je reucpere le message
        if (messageContainer && messageContainer.textContent.trim() !== "") {
            Swal.fire({
                icon: 'succes',
                title: 'Notification',
                text: messageContainer.textContent,
                confirmButtonText: 'OK'
            });
        }
    });