//show collections
//db.shows.find({})
//db.shows.findOne({ }) //FInd one with a critera
//db.tv.find({
//    language:"English"
//    //})

//db.tv.find({
//    language: {
//        $regex: 'english',
//        $options: 'i'//   }
//    
//})

//use shows
//db.tv.find({
//    genres: {
//        $in: ['Crime', 'Supernatural']//    },
//    "rating.average" :{ $gte:9}//})

// Find all unique values
//db.tv.distinct("genres")
//Count all shows
//db.tv.find().count()

// Count all english shows
//db.tv.find({language: {
//    $regex: 'eng',
//    $options: 'i'
//}
//}).count()

//db.tv.find({language: {
//    $regex: 'eng',
//    $options: 'i'}
//}).projection()

//db.tv.distinct("type")

//db.tv.find({
//    type: 
//        "Animation"
//    
//})

//db.tv.find({
//    'type': {
//        $regex: 'Animation',
//        $options:'i'}  
//})
//.sort({"rating.average": -1})
//.projection({_id:0, id:1, name:1, "rating.average":1, summary:1})



//db.tv.find({
//    name: "Beserk"//})

//db.friends.find()

//db.friends.updateOne(
//{'name': {$regex: 'red', $options:"i"}},
//{$set: {age: "45"}}
//)

//db.friends.find()

//db.friends.updateOne(
//{'name': {$regex: 'red', $options:"i"}},
//{$inc: {age: 1}}
//)

//use bgg
//
//db.comment.find(
//{"user": "samurai"}//)

//db.comments.find()

//db.comments.createIndex({
//    user: 1//})

//db.comments.createIndex({
//    c_text:"text"//})

db.comments.find(
    {
    $text: {$search: "bad trash garbage awful boring -fun"},
    {score: {$meta: "textScore"}    }
        })