package si.matjazcerkvenik.test.mongodb;


import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;


// https://www.mongodb.com/developer/quickstart/java-setup-crud-operations/
// https://github.com/mongodb-developer/java-quick-start
public class Start {

    public static void main(String... args) {

        String connectionString = "mongodb://admin:mongodbpassword@promvm:27017/test?w=majority&authSource=admin";

        // print all databases
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }

        // Inserting data
        // Connecting to a Collection
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            // Create a BSON Document
            Random rand = new Random();
            Document student = new Document("_id", new ObjectId());
            student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", Arrays.asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                            new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100)));

            // insert one document
            gradesCollection.insertOne(student);

            // insert many
            List<Document> grades = new ArrayList<>();
            for (double classId = 1d; classId <= 10d; classId++) {
                grades.add(generateNewGrade(10001d, classId));
            }
            gradesCollection.insertMany(grades, new InsertManyOptions().ordered(false));

        }

        // Reading data
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            // find one document with new Document
            Document student1 = gradesCollection.find(new Document("student_id", 10000)).first();
            System.out.println("Student 1: " + student1.toJson());

            // using filter
            gradesCollection.find(Filters.eq("student_id", 10001)).first();

            // Read many Documents
            gradesCollection.find(Filters.gte("student_id", 10000));

            // without Filters, instead using $gte operator, result is the same
            gradesCollection.find(new Document("student_id", new Document("$gte", 10000)));

            // Iterators
            FindIterable<Document> iterable = gradesCollection.find(Filters.gte("student_id", 10000));
            MongoCursor<Document> cursor = iterable.iterator();
            System.out.println("Student list with cursor: ");
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }

            // get documents as list
            List<Document> studentList = gradesCollection.find(Filters.gte("student_id", 10000)).into(new ArrayList<>());
            System.out.println("Student list with an ArrayList:");
            for (Document student : studentList) {
                System.out.println(student.toJson());
            }

            // consumers
            Consumer<Document> printConsumer = document -> System.out.println(document.toJson());
            gradesCollection.find(Filters.gte("student_id", 10000)).forEach(printConsumer);

            // Cursors, Sort, Skip, Limit, and Projections
            List<Document> docs = gradesCollection.find(Filters.and(Filters.eq("student_id", 10001), Filters.lte("class_id", 5)))
                    .projection(Projections.fields(Projections.excludeId(),
                            Projections.include("class_id", "student_id")))
                    .sort(Sorts.descending("class_id"))
                    .skip(2)
                    .limit(2)
                    .into(new ArrayList<>());
            System.out.println("Student sorted, skipped, limited and projected: ");
            for (Document student : docs) {
                System.out.println(student.toJson());
            }

        }


        // Update documents
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            // update one document
            Bson filter = Filters.eq("student_id", 10000);
            Bson updateOperation = Updates.set("comment", "You should learn MongoDB!");
            UpdateResult updateResult = gradesCollection.updateOne(filter, updateOperation);
            System.out.println("=> Updating the doc with {\"student_id\":10000}. Adding comment.");
            System.out.println(gradesCollection.find(filter).first().toJson(prettyPrint));
            System.out.println(updateResult);

            // Upsert - a mix between an insert operation and an update one (insert if it does not exist, otherwise update)
            filter = Filters.and(Filters.eq("student_id", 10002d), Filters.eq("class_id", 10d));
            updateOperation = Updates.push("comments", "You will learn a lot if you read the MongoDB blog!");
            UpdateOptions options = new UpdateOptions().upsert(true);
            updateResult = gradesCollection.updateOne(filter, updateOperation, options);
            System.out.println("\n=> Upsert document with {\"student_id\":10002.0, \"class_id\": 10.0} because it doesn't exist yet.");
            System.out.println(updateResult);
            System.out.println(gradesCollection.find(filter).first().toJson(prettyPrint));

            // Update many documents
            filter = Filters.eq("student_id", 10001);
            updateResult = gradesCollection.updateMany(filter, updateOperation);
            System.out.println("\n=> Updating all the documents with {\"student_id\":10001}.");
            System.out.println(updateResult);

            // The findOneAndUpdate method
            filter = Filters.eq("student_id", 10000);
            Bson update1 = Updates.inc("x", 10); // increment x by 10. As x doesn't exist yet, x=10.
            Bson update2 = Updates.rename("class_id", "new_class_id"); // rename variable "class_id" in "new_class_id".
            Bson update3 = Updates.mul("scores.0.score", 2); // multiply the first score in the array by 2.
            Bson update4 = Updates.addToSet("comments", "This comment is uniq"); // creating an array with a comment.
            Bson update5 = Updates.addToSet("comments", "This comment is uniq"); // using addToSet so no effect.
            Bson updates = Updates.combine(update1, update2, update3, update4, update5);
            // returns the old version of the document before the update.
            Document oldVersion = gradesCollection.findOneAndUpdate(filter, updates);
            System.out.println("\n=> FindOneAndUpdate operation. Printing the old version by default:");
            System.out.println(oldVersion.toJson(prettyPrint));
            // but I can also request the new version
            filter = Filters.eq("student_id", 10001);
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = gradesCollection.findOneAndUpdate(filter, updates, optionAfter);
            System.out.println("\n=> FindOneAndUpdate operation. But we can also ask for the new version of the doc:");
            System.out.println(newVersion.toJson(prettyPrint));

        }

        // Delete Operations
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            // delete one document
            Bson filter = Filters.eq("student_id", 10000);
            DeleteResult result = gradesCollection.deleteOne(filter);
            System.out.println(result);

            // FindOneAndDelete() - get the document before it is deleted
            Bson filter2 = Filters.eq("student_id", 10002);
            Document doc = gradesCollection.findOneAndDelete(filter2);
            System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));

            // Delete Many Documents
            Bson filter3 = Filters.gte("student_id", 10005);
            DeleteResult resultDeleteMany = gradesCollection.deleteMany(filter3);
            System.out.println(resultDeleteMany);

            // Delete a Collection
            // gradesCollection.drop();
        }





    }

    private static Document generateNewGrade(double studentId, double classId) {

        Random rand = new Random();

        List<Document> scores = Arrays.asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                new Document("type", "homework").append("score", rand.nextDouble() * 100),
                new Document("type", "homework").append("score", rand.nextDouble() * 100));

        return new Document("_id", new ObjectId()).append("student_id", studentId)
                .append("class_id", classId)
                .append("scores", scores);
    }

}
