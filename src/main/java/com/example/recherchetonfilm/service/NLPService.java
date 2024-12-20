package com.example.recherchetonfilm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@Service
public class NLPService {

    private StanfordCoreNLP pipeline;

    public NLPService() {
        // Configuration de Stanford NLP
        Properties props = new Properties();
        // L'ordre des annotateurs est important
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        this.pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Extraction des mots-clés à partir d'un texte.
     */
    public List<String> extractKeywords(String text) {
        List<String> keywords = new ArrayList<>();
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        // Extraire les mots-clés basés sur les entités nommées
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if ("PERSON".equals(ner) || "LOCATION".equals(ner) || "ORGANIZATION".equals(ner)) {
                    keywords.add(word);
                }
            }
        }
        return keywords;
    }
}
    