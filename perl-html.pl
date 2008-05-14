#! /usr/bin/perl
$fichero=shift;
open FICHERO, $fichero;
@array=<FICHERO>;
close FICHERO;
open FICHERO, ">$fichero";
foreach (@array)
{
   $_ =~ s/á/&aacute;/g;
   $_ =~ s/é/&eacute;/g;
   $_ =~ s/í/&iacute;/g;
   $_ =~ s/ó/&oacute;/g;
   $_ =~ s/ú/&uacute;/g;
   $_ =~ s/ñ/&ntilde;/g;
   print FICHERO "$_";
}
close FICHERO;
