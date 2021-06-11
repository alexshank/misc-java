package com.shank.chessClone.infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.shank.chessClone.model.ChessPiece;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ChessPieceJsonDeserializer extends JsonDeserializer<ChessPiece> {


    @Override
    public ChessPiece deserialize(JsonParser jsonParser,
                                  DeserializationContext deserializationContext)
                                  throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String shorthand = jsonParser.getCodec().treeToValue(treeNode.get("shorthand"), String.class);
        return (ChessPieceFactory.newChessPiece(shorthand));
    }
}
