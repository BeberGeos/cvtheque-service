<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="3.0" xmlns:tns="http://www.example.org/schemaCV"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:import href="templateCompetenceTechnique.xsl"></xsl:import>
    <xsl:import href="templateCompetenceFonctionnelle.xsl"></xsl:import>
    <xsl:import href="templateExperience.xsl"></xsl:import>


    <xsl:output method="html"
                doctype-system="about:legacy-compat"
                encoding="UTF-8"
                indent="yes"/>

    <xsl:template match="/tns:cv">
        <html>
            <head>
                <title>CV de&#160;<xsl:value-of select="identite/prenom"/>&#160;<xsl:value-of select="identite/nom"/></title>

                <link rel="stylesheet" href="css/bootstrap.min.css"/>
                <link rel="stylesheet" href="css/style.css"/>

            </head>
            <body>
                <div class="bs-docs-header" id="header">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="fonts/avatar.jpg" alt="Avatar" class="img-thumbnail" style="width: 140px; height: 140px;"/>
                            </div>
                            <div class="col-md-8">
                                <h1><xsl:value-of select="fonction/titre"/></h1>
                                <p><b><xsl:value-of select="fonction/anneeExperience"/></b> années d'expérience</p>
                                <p>Habite à <b><xsl:value-of select="identite/adresse/ville"/></b></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <xsl:call-template name="competenceTechniqueTemplate"></xsl:call-template>
                        </div>

                        <div class="col-lg-6">
                            <xsl:call-template name="competenceFonctionnelleTemplate"></xsl:call-template>
                        </div>
                    </div>
                    <div class="bordure"></div>
                    <div id="content-experience">
                        <xsl:call-template name="experienceTemplate"></xsl:call-template>
                    </div>
                </div>
                <div class="footer">
                    <div class="container">
                        Copyright © CV AC, 2014
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>