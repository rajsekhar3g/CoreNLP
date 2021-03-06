package edu.stanford.nlp.pipeline;

/**
 * Wrapper around a CoreMap representing a sentence.  Adds some helpful methods.
 *
 */

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;
import java.util.stream.Collectors;

public class CoreSentence {

  private CoreDocument document;
  private CoreMap sentenceCoreMap;
  private List<CoreEntityMention> entityMentions;

  public CoreSentence(CoreDocument myDocument, CoreMap coreMapSentence) {
    this.document = myDocument;
    this.sentenceCoreMap = coreMapSentence;
  }

  /** create list of CoreEntityMention's based on the CoreMap's entity mentions **/
  public void wrapEntityMentions() {
    if (this.sentenceCoreMap.get(CoreAnnotations.MentionsAnnotation.class) != null) {
      entityMentions = this.sentenceCoreMap.get(CoreAnnotations.MentionsAnnotation.class).
          stream().map(coreMapEntityMention -> new CoreEntityMention(coreMapEntityMention)).collect(Collectors.toList());
    }
  }

  /** get the underlying CoreMap if need be **/
  public CoreMap coreMap() {
    return sentenceCoreMap;
  }

  /** full text of the sentence **/
  public String text() {
    return sentenceCoreMap.get(CoreAnnotations.TextAnnotation.class);
  }

  /** list of tokens **/
  public List<CoreLabel> tokens() {
    return sentenceCoreMap.get(CoreAnnotations.TokensAnnotation.class);
  }

  /** constituency parse **/
  public Tree constituencyParse() {
    return sentenceCoreMap.get(TreeCoreAnnotations.TreeAnnotation.class);
  }

  /** dependency parse **/
  public SemanticGraph dependencyParse() {
    return sentenceCoreMap.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class);
  }

  /** sentiment **/
  public String sentiment() {
    return sentenceCoreMap.get(SentimentCoreAnnotations.SentimentClass.class);
  }

  /** sentiment tree **/
  public Tree sentimentTree() {
    return sentenceCoreMap.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
  }

  public List<CoreEntityMention> entityMentions() {
    return this.entityMentions;
  }


}
