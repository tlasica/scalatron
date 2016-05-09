/**
 * Copyright (C) 2009-2010 the original author or authors.
 */
package org.fusesource.scalamd.test

import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils
import org.fusesource.scalamd.Markdown
import org.scalatest.matchers.{BeMatcher, MatchResult}
import org.scalatest.{FeatureSpec, MustMatchers}

import scala.language.postfixOps

object MarkdownSpec extends FeatureSpec with MustMatchers {

  class MarkdownMatcher extends BeMatcher[String] {
    def apply(testCase: String): MatchResult = {
      val textFile = this.getClass.getResourceAsStream("/" + testCase + ".text")
      val htmlFile = this.getClass.getResourceAsStream("/" + testCase + ".html")
      val text = Markdown(IOUtils.toString(textFile, "ISO-8859-1")).trim
      val html = IOUtils.toString(htmlFile, "ISO-8859-1").trim
      val diffIndex = StringUtils.indexOfDifference(text, html)
      val diff = StringUtils.difference(text, html)
      MatchResult(
        diffIndex == -1,
        "\"" + testCase + "\" fails at " + diffIndex + ": " + StringUtils.abbreviate(diff, 32),
        "\"" + testCase + "\" is fine"
      )
    }
  }
  val fine = new MarkdownMatcher

  feature("MarkdownProcessor") {

    scenario("Images") {
      "Images" must be (fine)
    }
    scenario("TOC") {
      "TOC" must be (fine)
    }
    scenario("Amps and angle encoding") {
      "Amps and angle encoding" must be (fine)
    }
    scenario("Auto links") {
      "Auto links" must be (fine)
    }
    scenario("Backslash escapes") {
      "Backslash escapes" must be (fine)
    }
    scenario("Blockquotes with code blocks") {
      "Blockquotes with code blocks" must be (fine)
    }
    scenario("Hard-wrapped paragraphs with list-like lines") {
      "Hard-wrapped paragraphs with list-like lines" must be (fine)
    }
    scenario("Horizontal rules") {
      "Horizontal rules" must be (fine)
    }
    scenario("Inline HTML (Advanced)") {
      "Inline HTML (Advanced)" must be (fine)
    }
    scenario("Inline HTML (Simple)") {
      "Inline HTML (Simple)" must be (fine)
    }
    scenario("Inline HTML comments") {
      "Inline HTML comments" must be (fine)
    }
    scenario("Links, inline style") {
      "Links, inline style" must be (fine)
    }
    scenario("Links, reference style") {
      "Links, reference style" must be (fine)
    }
    scenario("Literal quotes titles") {
      "Literal quotes titles" must be (fine)
    }
    scenario("Nested blockquotes") {
      "Nested blockquotes" must be (fine)
    }
    scenario("Ordered and unordered lists") {
      "Ordered and unordered lists" must be (fine)
    }
    scenario("Strong and em together") {
      "Strong and em together" must be (fine)
    }
    scenario("Tabs") {
      "Tabs" must be (fine)
    }
    scenario("Tidyness") {
      "Tidyness" must be (fine)
    }
    scenario("SmartyPants") {
      "SmartyPants" must be (fine)
    }
    scenario("Markdown inside inline HTML") {
      "Markdown inside inline HTML" must be (fine)
    }
    scenario("Spans inside headers") {
      "Spans inside headers" must be (fine)
    }
    scenario("Macros") {
        "Macros" must be (fine)
    }
  }
}
