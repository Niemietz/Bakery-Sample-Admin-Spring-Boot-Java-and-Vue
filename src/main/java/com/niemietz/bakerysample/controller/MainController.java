package com.niemietz.bakerysample.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")          // ← prefixo comum a tudo
public class MainController {

    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @GetMapping
    public String home() {
        return "null";
    }

    @GetMapping("/data")
    public String data() {
        String file = "src/main/resources/siteData.json";
        try {
            return readFileAsString(file);
        } catch (Exception ex) {
            return "null";
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestEntity<?> req) {
        System.out.println("*** Posting data: " + req);
        /*
        app.post('/', async (req, res) => {
            const {
                heroTitle,
                heroSubtitle,
                heroCTA,
                whyChooseUsTitle,
                whyChooseUsDescription,
                visitUs,
                visitUsDescription,
                visitUsCTA,
                address,
                phone
            } = req.body;

            let updatedData = merge(
                await loadData(),
                {
                    hero: {
                        title: heroTitle,
                        subtitle: heroSubtitle,
                        ctaText: heroCTA,
                    },
                    sections: {
                        whyChooseUs: {
                            title: whyChooseUsTitle,
                            description: whyChooseUsDescription,
                        },
                    },
                    footer: {
                        visitUs: visitUs,
                        visitUsDescription: visitUsDescription,
                        visitUsCta_text: visitUsCTA,
                        address: address,
                        phone: phone,
                    }
                }
            )

            try {
                await saveData(updatedData);
                res.redirect('/?success=true');
            } catch (err) {
                console.error(err);
                res.redirect('/?error=true');
            }
        });
        */

        return ResponseEntity.ok().build();
    }

    /*@GetMapping("/{id}")
    public String homeWithID(@PathVariable Long id) {
        return "{ \"mensagem\": \"The ID you sent was " + id.toString() + "\" }";
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto p) {

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(...) {

    }*/
}
