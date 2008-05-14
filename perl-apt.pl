#! /usr/bin/perl
$fichero=shift;
open FICHERO, $fichero;
@array=<FICHERO>;
close FICHERO;
open FICHERO, ">$fichero";
foreach (@array)
{
   $_ =~ s/á/\\uOOE1/g;
   $_ =~ s/é/\\uOOE9/g;
   $_ =~ s/í/\\uOOED/g;
   $_ =~ s/ó/\\uOOF3/g;
   $_ =~ s/ú/\\uOOFA/g;
   $_ =~ s/ñ/\\uOOF1/g;
   print FICHERO "$_";
}
close FICHERO;
