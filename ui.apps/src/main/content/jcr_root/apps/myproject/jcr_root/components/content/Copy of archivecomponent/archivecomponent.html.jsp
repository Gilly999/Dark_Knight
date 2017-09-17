

<sly data-sly-test='${wcmmode.edit}'>

I am in Edit Mode Manikanta

    archivecomponent.html.jsp

</sly>

<sly data-sly-test='${wcmmode.preview}'>

I am in Preview mode Manikanta

   archivecomponent.html.jsp

</sly>



<!-- ARTICLE 1 -->               
               <article class="margin-bottom">
                  <div class="post-1 line">
                     <!-- image -->                 
                     <div class="s-12 l-11 post-image">                   
                        <a href="post-1.html"><img src="${properties.ImagePath}" alt="Fashion"></a>              
                     </div>
                     <!-- date -->                 
                     <div class="s-12 l-1 post-date">
                        <p class="date">${properties.date}</p>
                        <p class="month">${properties.month}</p>
                     </div>
                  </div>
                  <!-- text -->                 
                  <div class="post-text">
                     <a href="post-1.html">
                        <h2>${Properties.h2text}</h2>
                     </a>
                     <p>
                        ${properties.para}
                     </p>
                     <a class="continue-reading" href="post-1.html">Continue reading</a>
                  </div>
               </article>




